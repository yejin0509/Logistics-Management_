package kanri.model;
//Product 자바 빈스
public class Product {
	private String product_Id;
	private String product_Type;
	private String product_Name;
	private String company;
	private int price;
	private String content;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String product_Id, String product_Type, String product_Name, String company, int price,
			String content) {
		super();
		this.product_Id = product_Id;
		this.product_Type = product_Type;
		this.product_Name = product_Name;
		this.company = company;
		this.price = price;
		this.content = content;
	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	public String getProduct_Type() {
		return product_Type;
	}

	public void setProduct_Type(String product_Type) {
		this.product_Type = product_Type;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
