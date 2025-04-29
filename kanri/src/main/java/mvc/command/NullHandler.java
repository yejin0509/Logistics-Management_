package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 핸들러 에러 날 때
public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
