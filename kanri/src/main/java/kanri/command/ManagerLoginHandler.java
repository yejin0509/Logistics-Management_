package kanri.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;
import jdbc.connection.ConnectionProvider;
import kanri.dao.ManagerDao;
import kanri.model.Manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ManagerLoginHandler implements CommandHandler {

    private static final String FORM_VIEW = "/managerLoginForm.jsp";
    private ManagerDao managerDao = new ManagerDao();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return FORM_VIEW;
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
        String manager_Id = req.getParameter("manager_Id");
        String password = req.getParameter("password");

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        if (manager_Id == null || manager_Id.trim().isEmpty()) {
            errors.put("manager_Id", Boolean.TRUE);
        }
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", Boolean.TRUE);
        }

        if (!errors.isEmpty()) {
            return FORM_VIEW;
        }

        try (Connection conn = ConnectionProvider.getConnection()) {
            Manager manager = managerDao.selectById(conn, manager_Id);
            if (manager == null || !manager.getPassword().equals(password)) {
                errors.put("idOrPwNotMatch", Boolean.TRUE);
                return FORM_VIEW;
            }

            // 로그인 성공 → 세션에 저장
            HttpSession session = req.getSession();
            session.setAttribute("authManager", manager);

            // 로그인 성공 후 이동할 경로 (수정 필요)
            return "/managerMain.jsp";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
