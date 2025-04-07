package mvc.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.command.SearchByIdHandler;

public class ControllerServlet extends HttpServlet {
	private Map<String, CommandHandler> command_Map = new HashMap<>();

	@Override
	public void init() throws ServletException {
		// URL 패턴과 핸들러를 매핑
		command_Map.put("/SearchByIdHandler", new SearchByIdHandler());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String command = req.getServletPath(); // 요청 URL에서 패턴 추출
		CommandHandler handler = command_Map.getOrDefault(command, new NullHandler()); // 핸들러 없으면 NullHandler 실행

		try {
			// 핸들러 실행 및 JSP 페이지 경로 반환
			String viewPage = handler.process(req, res);

			if (viewPage != null) {
				req.getRequestDispatcher(viewPage).forward(req, res); // JSP로 포워드
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}