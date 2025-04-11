package kanri.model;


//Manager 자바 빈스
public class Manager {
	private String manager_Id;
	private String password;

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(String manager_Id, String password) {
		this.manager_Id = manager_Id;
		this.password = password;
	}

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

	
}
