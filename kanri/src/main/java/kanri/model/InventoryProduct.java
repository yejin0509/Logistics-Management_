package kanri.model;

//InventoryProduct 자바 빈스
//Inventory랑 Product join 하려고 만든 자바 빈스
//근데 안 만들고 기본 저장 영역(setAttribute 같은 거) 썼어도 됐었나.... 모름
public class InventoryProduct {
	private String product_Id;
	private String product_Type;
	private String product_Name;
	private String company;
	private int price;
	private String content;
	private String location;
	private int stock;

	public InventoryProduct() {
		// TODO Auto-generated constructor stub
	}

	public InventoryProduct(String product_Id, String product_Type, String product_Name, String company, int price,
			String content, String location, int stock) {
		this.product_Id = product_Id;
		this.product_Type = product_Type;
		this.product_Name = product_Name;
		this.company = company;
		this.price = price;
		this.content = content;
		this.location = location;
		this.stock = stock;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	

}
