package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//기본 핸들러 인터페이스
public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
