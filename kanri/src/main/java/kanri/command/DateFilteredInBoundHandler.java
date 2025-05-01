package kanri.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DateFilteredInBoundService;
import kanri.service.InBoundPage;
import mvc.command.CommandHandler;

public class DateFilteredInBoundHandler implements CommandHandler {

    private DateFilteredInBoundService service = new DateFilteredInBoundService(); 
    // 날짜 기준 입고 목록 서비스 객체 생성
    // 日付でフィルタリングする入庫リストサービスのインスタンス生成（ひづけ で フィルタリング する にゅうこ リスト）

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
        String in_Date = req.getParameter("in_Date"); 
        // 요청 파라미터에서 날짜 받기
        // リクエストパラメータから日付を取得（ひづけ を しゅとく）

        if (in_Date == null || in_Date.trim().isEmpty()) {
            req.setAttribute("error", "주문 날짜를 선택해주세요."); 
            // 날짜를 선택하지 않았을 때 에러 메시지 설정
            // 日付が選択されていない場合はエラーメッセージを設定（ひづけ が せんたく されていない ばあい）

            return "/WEB-INF/view/customerScan.jsp"; 
            // 다시 검색 페이지로 이동
            // スキャンページに戻る（もどる）
        }
        
        SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd"); 
        // 날짜 형식 지정
        // 日付フォーマットの指定（ひづけ フォーマット の してい）

        Date in_dateDate = date_Format.parse(in_Date); 
        // 문자열 날짜를 Date 객체로 변환
        // 日付文字列をDateオブジェクトに変換（へんかん）

        InBoundPage inBoundPage = service.getFilteredList(in_dateDate); 
        // 필터링된 입고 데이터 목록 가져오기
        // フィルターされた入庫データリストを取得（にゅうこ データ）

        req.setAttribute("InBoundPage", inBoundPage); 
        // JSP에서 사용할 수 있도록 request에 데이터 저장
        // JSPで使えるようにリクエストにデータを保存（ほぞん）

        return "/WEB-INF/view/customerScan.jsp"; 
        // 결과 페이지로 이동
        // 結果ページへ移動（けっか ページ へ いどう）
    }
}
