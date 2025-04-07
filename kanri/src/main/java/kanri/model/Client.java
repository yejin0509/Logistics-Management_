package kanri.model;

public class Client {
	private String client_Id;
	private String password;
	private String company;
	private String c_Number;
	private String address;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String client_Id, String password, String company, String c_Number, String address) {
		super();
		this.client_Id = client_Id;
		this.password = password;
		this.company = company;
		this.c_Number = c_Number;
		this.address = address;
	}

	public String getClient_id() {
		return client_Id;
	}

	public void setClient_id(String client_Id) {
		this.client_Id = client_Id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


}
