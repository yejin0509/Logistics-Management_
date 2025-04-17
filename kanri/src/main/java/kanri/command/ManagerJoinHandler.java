package kanri.command;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.ManagerJoinRequest;
import kanri.service.ManagerJoinService;
import kanri.service.DuplicateIdException;
import mvc.command.CommandHandler;

public class ManagerJoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/managerJoinForm.jsp";
	private ManagerJoinService joinService = new ManagerJoinService();

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
		ManagerJoinRequest joinRequest = new ManagerJoinRequest();

		joinRequest.setManager_Id(req.getParameter("manager_Id"));
		joinRequest.setPassword(req.getParameter("password"));
		joinRequest.setConfirmPassword(req.getParameter("confirmPassword"));
		joinRequest.setRegisterCode(req.getParameter("registerCode"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		joinRequest.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinRequest);
			return "/managerJoinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
