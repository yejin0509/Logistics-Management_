package kanri.model;

public class Product {


	private String product_Id;
	private String product_Type;
	private String product_Name;
	private String company;
	private String content;
	private int price;
	
	
	public Product(String product_Id, String product_Type, String product_Name, String company, String content,
			int price) {
		super();
		this.product_Id = product_Id;
		this.product_Type = product_Type;
		this.product_Name = product_Name;
		this.company = company;
		this.content = content;
		this.price = price;
	}
	
	

	/**
	 * @return the product_Id
	 */
	public String getProduct_Id() {
		return product_Id;
	}
	
	
	/**
	 * @return the product_Type
	 */
	public String getProduct_Type() {
		return product_Type;
	}
	
	
	/**
	 * @return the product_Name
	 */
	public String getProduct_Name() {
		return product_Name;
	}
	
	
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	
	/**
	 * @param product_Id the product_Id to set
	 */
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}
	
	
	/**
	 * @param product_Type the product_Type to set
	 */
	public void setProduct_Type(String product_Type) {
		this.product_Type = product_Type;
	}
	
	
	/**
	 * @param product_Name the product_Name to set
	 */
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}
	
	
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
}
