package kanri.model;

import java.util.Date;

public class OutBound {

	private String client_Id;
	private String product_Id;
	private Date out_Date;
	private int out_Count;
	private int price;

	public OutBound(String client_Id, String product_Id, Date out_Date, int out_Count, int price) {
		super();
		this.client_Id = client_Id;
		this.product_Id = product_Id;
		this.out_Date = out_Date;
		this.out_Count = out_Count;
		this.price = price;
	}

	/**
	 * @return the client_Id
	 */
	public String getClient_Id() {
		return client_Id;
	}

	/**
	 * @return the product_Id
	 */
	public String getProduct_Id() {
		return product_Id;
	}

	/**
	 * @return the out_Date
	 */
	public Date getOut_Date() {
		return out_Date;
	}

	/**
	 * @return the out_Count
	 */
	public int getOut_Count() {
		return out_Count;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param client_Id the client_Id to set
	 */
	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}

	/**
	 * @param product_Id the product_Id to set
	 */
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	/**
	 * @param out_Date the out_Date to set
	 */
	public void setOut_Date(Date out_Date) {
		this.out_Date = out_Date;
	}

	/**
	 * @param out_Count the out_Count to set
	 */
	public void setOut_Count(int out_Count) {
		this.out_Count = out_Count;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
}