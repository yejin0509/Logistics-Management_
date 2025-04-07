package kanri.model;

public class Inventory {
	private String product_id;
	private String location;
	private int stock;

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	public Inventory(String product_id, String location, int stock) {
		super();
		this.product_id = product_id;
		this.location = location;
		this.stock = stock;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
