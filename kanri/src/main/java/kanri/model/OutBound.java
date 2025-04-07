package kanri.model;

import java.util.Date;

public class OutBound {
	private String client_Id;
	private String product_Id;
	private Date out_Date;
	private int out_Count;
	private int price;

	public OutBound() {
		// TODO Auto-generated constructor stub
	}

	public OutBound(String client_Id, String product_Id, Date out_Date, int out_Count, int price) {
		super();
		this.client_Id = client_Id;
		this.product_Id = product_Id;
		this.out_Date = out_Date;
		this.out_Count = out_Count;
		this.price = price;
	}

	public String getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	public Date getOut_Date() {
		return out_Date;
	}

	public void setOut_Date(Date out_Date) {
		this.out_Date = out_Date;
	}

	public int getOut_Count() {
		return out_Count;
	}

	public void setOut_Count(int out_Count) {
		this.out_Count = out_Count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
