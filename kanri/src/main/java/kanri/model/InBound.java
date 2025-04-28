package kanri.model;

import java.util.Date;

public class InBound {

	private String manager_Id;
	private String product_Id;
	private Date in_Date;
	private int in_Count;
	private int price;

	public InBound(String manager_Id, String product_Id, Date date, int in_Count, int price) {
		super();
		this.manager_Id = manager_Id;
		this.product_Id = product_Id;
		this.in_Date = date;
		this.in_Count = in_Count;
		this.price = price;
	}

	/**
	 * @return the client_Id
	 */
	public String getManager_Id() {
		return manager_Id;
	}

	/**
	 * @return the product_Id
	 */
	public String getProduct_Id() {
		return product_Id;
	}

	/**
	 * @return the date
	 */
	public Date getIn_Date() {
		return in_Date;
	}

	/**
	 * @return the in_Count
	 */
	public int getIn_Count() {
		return in_Count;
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
	public void setManager_Id(String manager_Id) {
		this.manager_Id = manager_Id;
	}

	/**
	 * @param product_Id the product_Id to set
	 */
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.in_Date = date;
	}

	/**
	 * @param in_Count the in_Count to set
	 */
	public void setIn_Count(int in_Count) {
		this.in_Count = in_Count;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
