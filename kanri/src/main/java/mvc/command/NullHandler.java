package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 핸들러 에러 날 때 / ハンドラーエラー発生時
public class NullHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // TODO Auto-generated method stub
        // HTTP 404 오류를 클라이언트에 전송 / クライアントにHTTP 404エラーを送信
        res.sendError(HttpServletResponse.SC_NOT_FOUND);
        return null; // null을 반환 / nullを返す
    }
}
