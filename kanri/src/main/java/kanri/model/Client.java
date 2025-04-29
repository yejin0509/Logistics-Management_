package kanri.model;

//Client 자바 빈스
public class Client {
	private String client_Id;
	private String password;
	private String company;
	private String c_Number;
	private String address;

	public Client() {
	}

	public Client(String client_Id, String password, String company, String c_Number, String address) {
		super();
		this.client_Id = client_Id;
		this.password = password;
		this.company = company;
		this.c_Number = c_Number;
		this.address = address;
	}

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
