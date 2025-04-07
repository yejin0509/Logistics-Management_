package kanri.model;

public class Manager {
	private String manager_id;
	private String password;

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(String manager_id, String password) {
		this.manager_id = manager_id;
		this.password = password;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
