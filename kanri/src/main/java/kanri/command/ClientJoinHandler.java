package kanri.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DuplicateIdException;
import kanri.service.ClientJoinRequest;
import kanri.service.ClientJoinService;
import mvc.command.CommandHandler;

public class ClientJoinHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/clientJoinForm.jsp";
	private ClientJoinService joinService = new ClientJoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
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
		ClientJoinRequest joinRequest = new ClientJoinRequest();

		joinRequest.setClient_Id(req.getParameter("client_Id"));
		joinRequest.setPassword(req.getParameter("password"));
		joinRequest.setConfirmPassword(req.getParameter("confirmPassword"));
		joinRequest.setCompany(req.getParameter("company"));
		joinRequest.setAddress(req.getParameter("address"));
		joinRequest.setTosAgree(req.getParameter("tosAgree") != null);
		joinRequest.setPrivacyAgree(req.getParameter("privacyAgree") != null);

		// 연락처 조합
		String part1 = req.getParameter("c_Number1");
		String part2 = req.getParameter("c_Number2");
		String part3 = req.getParameter("c_Number3");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 유효성 검사 전에 연락처 빈 값 여부 확인
		if (part1 == null || part1.trim().isEmpty()
				|| part2 == null || part2.trim().isEmpty()
				|| part3 == null || part3.trim().isEmpty()) {
			errors.put("c_Number", Boolean.TRUE);
		} else {
			String fullNumber = part1 + "-" + part2 + "-" + part3;
			joinRequest.setC_Number(fullNumber);
		}

		joinRequest.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinRequest);
			return "/clientJoinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
