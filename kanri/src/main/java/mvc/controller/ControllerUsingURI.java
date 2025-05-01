package mvc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {

    private Map<String, CommandHandler> commandHandlerMap = new HashMap<>(); // URI와 핸들러 매핑을 위한 맵 / URIとハンドラーのマッピング用のマップ

    public void init() throws ServletException {
        String config_URI = getInitParameter("configURI"); // web.xml에 설정된 configURI 가져오기 / web.xmlに設定されたconfigURIを取得
        Properties prop = new Properties(); // Properties 객체 생성 / Propertiesオブジェクトを作成
        String configFilePath = getServletContext().getRealPath(config_URI); // 서버의 실제 경로 / サーバーの実際のパス
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            // try-resource 문이라 자동반환, return값이 없음 / try-resource文なので自動的にリソースが閉じられます。戻り値はありません。
            prop.load(fis); // 프로퍼티 파일 읽기 / プロパティファイルを読み込む
        } catch (IOException e) {
            throw new ServletException(e); // 예외 처리 / 例外処理
        }

        Iterator keyIter = prop.keySet().iterator(); // 프로퍼티의 키를 순차적으로 가져오기 / プロパティのキーを順番に取得
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next(); // 커맨드 문자열 / コマンド文字列
            
            String handler_Class_Name = prop.getProperty(command); // 핸들러 클래스 이름 가져오기 / ハンドラークラス名を取得
            try {
                Class<?> handlerClass = Class.forName(handler_Class_Name); // 클래스 로딩 / クラスのロード
                CommandHandler handler_Instance = (CommandHandler) handlerClass.newInstance(); // 핸들러 인스턴스 생성 / ハンドラーインスタンスを生成
                commandHandlerMap.put(command, handler_Instance); // URI와 핸들러를 매핑 / URIとハンドラーをマッピング
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServletException(e); // 예외 처리 / 例外処理
            }
        }
    }

    // GET 요청을 처리하는 메서드 / GETリクエストを処理するメソッド
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response); // GET 방식으로도 process 메서드로 처리 / GET方式でもprocessメソッドで処理
    }

    // POST 요청을 처리하는 메서드 / POSTリクエストを処理するメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response); // POST 방식으로도 process 메서드로 처리 / POST方式でもprocessメソッドで処理
    }

    // GET과 POST 방식에서 공통으로 호출되는 메서드 / GETとPOST方式で共通で呼び出されるメソッド
    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getRequestURI(); // URI 가져오기 / URIを取得

        // URI에서 컨텍스트 경로를 제외한 부분을 command로 설정 / URIからコンテキストパスを除いた部分をcommandとして設定
        if (command.indexOf(request.getContextPath()) == 0) {
            command = command.substring(request.getContextPath().length());
        }

        CommandHandler handler = commandHandlerMap.get(command); // URI에 해당하는 핸들러 찾기 / URIに対応するハンドラーを検索
        if (handler == null) {
            handler = new NullHandler(); // 핸들러가 없으면 NullHandler 사용 / ハンドラーがない場合はNullHandlerを使用
        }
        
        String view_Page = null; // 뷰 페이지 / ビューページ
        try {
            view_Page = handler.process(request, response); // 핸들러의 process 메서드 호출 / ハンドラーのprocessメソッドを呼び出し
        } catch (Exception e) {
            throw new ServletException(e); // 예외 처리 / 例外処理
        }
        
        if (view_Page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(view_Page); // 뷰 페이지로 포워딩 / ビューページにフォワード
            dispatcher.forward(request, response);
        }
    }

}
