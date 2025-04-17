package kanri.service;

import java.util.Map;

public class ClientJoinRequest {

	private String client_Id;
	private String password;
	private String confirmPassword;
	private String company;
	private String c_Number;
	private String address;
	private boolean tosAgree;
	private boolean privacyAgree;

	// --- Getter & Setter (함수명: 카멜표기법) ---
	public String getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getC_Number() {
		return c_Number;
	}

	public void setC_Number(String c_Number) {
		this.c_Number = c_Number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isTosAgree() {
		return tosAgree;
	}

	public void setTosAgree(boolean tosAgree) {
		this.tosAgree = tosAgree;
	}

	public boolean isPrivacyAgree() {
		return privacyAgree;
	}

	public void setPrivacyAgree(boolean privacyAgree) {
		this.privacyAgree = privacyAgree;
	}

	// --- 비밀번호 확인 ---
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}

	// --- 유효성 검사 ---
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, client_Id, "client_Id");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		checkEmpty(errors, company, "company");
		checkEmpty(errors, c_Number, "c_Number");
		checkEmpty(errors, address, "address");

		if (!errors.containsKey("confirmPassword")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
		if (!tosAgree) {
			errors.put("tosAgree", Boolean.TRUE);
		}
		if (!privacyAgree) {
			errors.put("privacyAgree", Boolean.TRUE);
		}
	}

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
}
