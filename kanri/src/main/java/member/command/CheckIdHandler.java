package member.command;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientDao;
import kanri.model.Client;
import mvc.command.CommandHandler;

import java.sql.Connection;

public class CheckIdHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");
        boolean duplicated = false;

        try (Connection conn = ConnectionProvider.getConnection()) {
            ClientDao memberDao = new ClientDao();
            Client member = memberDao.selectById(conn, id);
            if (member != null) {
                duplicated = true;
            }
        }

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        if (duplicated) {
            out.println("<script>alert('이미 사용 중인 아이디입니다.'); window.close();</script>");
        } else {
            out.println("<script>alert('사용 가능한 아이디입니다.'); window.close();</script>");
        }
        return null;
    }
}
