package mvc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {

	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	public void init() throws ServletException {
		String config_URI = getInitParameter("configURI");
		// web에 init에 들어있는 값의 이름
		Properties prop = new Properties();
		// 문자열로 이루어진 모음집
		String configFilePath = getServletContext().getRealPath(config_URI);
		// 서버의 실제 경로
		try (FileInputStream fis = new FileInputStream(configFilePath)) {
			// try-resource 문이라 자동반환, return값이 없음

			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
			// TODO: handle exception
		}
		Iterator keyIter = prop.keySet().iterator();
		// 컬렉션 안에 정보 꺼낼 때 쓰는 인터페이스
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
		
			String handler_Class_Name = prop.getProperty(command);
			// 값이 넘어옴 //키 입력
			try {
				Class<?> handlerClass = Class.forName(handler_Class_Name);
				// 문자열을 Class 객체로 읽어냄 >>인스턴스화 하기 위해
				CommandHandler handler_Instance = (CommandHandler) handlerClass.newInstance();
				// 옛날 방식 인스턴스화(상관 X)
				commandHandlerMap.put(command, handler_Instance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
				// TODO: handle exception
			}

		}
	}

	//그니까 정보가 들어왔을 때 get인지 post인지에 따라 작동하는 메서드가 바뀔 거임
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	//근데 get이나 post나 작동방식은 같아가지고 process 메서드로 묶음
	//솔직히 내용은 잘 모르겠음
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getRequestURI();

		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		} // 문자열 추출

		CommandHandler handler = commandHandlerMap.get(command);
		if (handler == null) {
			handler = new NullHandler();
		}
		String view_Page = null;
		try {
			view_Page = handler.process(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		if (view_Page != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view_Page);
			dispatcher.forward(request, response);
		}
	}

}
