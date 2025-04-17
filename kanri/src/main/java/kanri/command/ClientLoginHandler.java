package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ClientLoginHandler implements CommandHandler {

    // 보여줄 JSP 경로
    private static final String FORM_VIEW = "/clientLoginForm.jsp";

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            // 로그인 폼 화면 보여주기
            return FORM_VIEW;

        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            // 나중에 로그인 검증 추가할 수 있음
            return FORM_VIEW;

        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }
}
