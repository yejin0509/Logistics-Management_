package kanri.model;

public class Client {
	private String client_id;
	private String password;
	private String company;
	private String c_number;
	private String address;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String client_id, String password, String company, String c_number, String address) {
		super();
		this.client_id = client_id;
		this.password = password;
		this.company = company;
		this.c_number = c_number;
		this.address = address;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
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

	public String getC_number() {
		return c_number;
	}

	public void setC_number(String c_number) {
		this.c_number = c_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
