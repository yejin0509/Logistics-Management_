package kanri.model;

public class Inventory {

	private String product_Id;
	private String location;
	private int stock;

	public Inventory(String product_Id, String location, int stock) {
		super();
		this.product_Id = product_Id;
		this.location = location;
		this.stock = stock;
	}

	/**
	 * @return the product_Id
	 */
	public String getProduct_Id() {
		return product_Id;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param product_Id the product_Id to set
	 */
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
}
