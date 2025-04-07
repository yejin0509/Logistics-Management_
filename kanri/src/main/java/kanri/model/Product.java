package kanri.model;

public class Product {
	private String product_id;
	private String product_type;
	private String product_name;
	private String company;
	private int price;
	private String content;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String product_id, String product_type, String product_name, String company, int price,
			String content) {
		super();
		this.product_id = product_id;
		this.product_type = product_type;
		this.product_name = product_name;
		this.company = company;
		this.price = price;
		this.content = content;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
