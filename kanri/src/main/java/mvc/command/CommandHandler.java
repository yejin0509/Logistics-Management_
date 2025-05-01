package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 기본 핸들러 인터페이스 / 基本的なハンドラインターフェース
public interface CommandHandler {
    // 클라이언트의 요청을 처리하는 메소드 / クライアントからのリクエストを処理するメソッド
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception; 
}
