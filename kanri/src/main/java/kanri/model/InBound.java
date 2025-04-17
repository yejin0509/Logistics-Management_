package kanri.model;

import java.util.Date;
//InBound 자바 빈스
public class InBound {
	private String manager_Id;
	private String product_Id;
	private Date in_Date;
	private int in_Count;
	private int price;

	public InBound() {
		// TODO Auto-generated constructor stub
	}

	public InBound(String manager_Id, String product_Id, Date in_Date, int in_Count, int price) {
		super();
		this.manager_Id = manager_Id;
		this.product_Id = product_Id;
		this.in_Date = in_Date;
		this.in_Count = in_Count;
		this.price = price;
	}

	public String getManager_Id() {
		return manager_Id;
	}

	public void setManager_Id(String manager_Id) {
		this.manager_Id = manager_Id;
	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	public Date getIn_Date() {
		return in_Date;
	}

	public void setIn_Date(Date in_Date) {
		this.in_Date = in_Date;
	}

	public int getIn_Count() {
		return in_Count;
	}

	public void setIn_Count(int in_Count) {
		this.in_Count = in_Count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
