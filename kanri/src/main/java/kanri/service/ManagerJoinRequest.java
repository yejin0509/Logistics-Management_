package kanri.service;

import java.util.Map;

public class ManagerJoinRequest {

	private String manager_Id;
	private String password;
	private String confirmPassword;
	private String registerCode;

	// --- Getter & Setter ---
	public String getManager_Id() {
		return manager_Id;
	}

	public void setManager_Id(String manager_Id) {
		this.manager_Id = manager_Id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	// --- 비밀번호 일치 검사 ---
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}

	// --- 유효성 검사 ---
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, manager_Id, "manager_Id");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		checkEmpty(errors, registerCode, "registerCode");

		if (!errors.containsKey("confirmPassword") && !isPasswordEqualToConfirm()) {
			errors.put("notMatch", Boolean.TRUE);
		}

		if (!"0000".equals(registerCode)) {
			errors.put("registerCode", Boolean.TRUE);
		}
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
}
