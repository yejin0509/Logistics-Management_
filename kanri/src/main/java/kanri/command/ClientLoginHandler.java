package kanri.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientDao;
import kanri.dao.ManagerDao;
import kanri.model.Client;
import mvc.command.CommandHandler;

public class ClientLoginHandler implements CommandHandler {

    // 보여줄 JSP 경로
    private static final String FORM_VIEW = "/clientLoginForm.jsp";
    private ClientDao clientDao = new ClientDao();

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
    
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        String client_Id = req.getParameter("client_Id");
        String password = req.getParameter("password");

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        if (client_Id == null || client_Id.trim().isEmpty()) {
            errors.put("client_Id", Boolean.TRUE);
        }
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", Boolean.TRUE);
        }

        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }

        try (Connection conn = ConnectionProvider.getConnection()) {
        	Client client = clientDao.selectById(conn, client_Id);
            if (client == null || !client.getPassword().equals(password)) {
                errors.put("idOrPwNotMatch", Boolean.TRUE);
                return FORM_VIEW;
            }

            // 로그인 성공 → 세션에 저장
            HttpSession session = req.getSession();
            session.setAttribute("authClient", client);
            System.out.println("로그인 성공: 관리자 ID = " + client.getClient_Id());


            // 로그인 성공 후 이동할 경로 (수정 필요)
            return "/clientMenu.jsp";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
