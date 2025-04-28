package kanri.model;

public class Manager {

	private String manager_Id;
	private String password;

	public Manager(String manager_Id, String password) {
		super();
		this.manager_Id = manager_Id;
		this.password = password;
	}

	/**
	 * @return the manager_Id
	 */
	public String getManager_Id() {
		return manager_Id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param manager_Id the manager_Id to set
	 */
	public void setManager_Id(String manager_Id) {
		this.manager_Id = manager_Id;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
