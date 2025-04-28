package kanri.model;

public class Client {

	private String client_Id;
	private String password;
	private String company;
	private String c_Number;
	private String address;

	public Client(String client_Id, String password, String company, String c_Number, String address) {
		super();
		this.client_Id = client_Id;
		this.password = password;
		this.company = company;
		this.c_Number = c_Number;
		this.address = address;
	}

	/**
	 * @return the client_Id
	 */
	public String getClient_Id() {
		return client_Id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @return the c_Number
	 */
	public String getC_Number() {
		return c_Number;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param client_Id the client_Id to set
	 */
	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @param c_Number the c_Number to set
	 */
	public void setC_Number(String c_Number) {
		this.c_Number = c_Number;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
