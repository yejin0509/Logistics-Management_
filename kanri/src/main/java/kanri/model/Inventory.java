package kanri.model;

//Inventory 자바 빈스
public class Inventory {
	private String product_Id;
	private String location;
	private int stock;

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	public Inventory(String product_Id, String location, int stock) {
		this.product_Id = product_Id;
		this.location = location;
		this.stock = stock;
	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
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
