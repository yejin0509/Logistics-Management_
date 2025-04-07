package kanri.model;

import java.util.Date;

public class Outbound {
	private String client_id;
	private String product_id;
	private Date out_date;
	private int out_count;
	private int price;

	public Outbound() {
		// TODO Auto-generated constructor stub
	}

	public Outbound(String client_id, String product_id, Date out_date, int out_count, int price) {
		super();
		this.client_id = client_id;
		this.product_id = product_id;
		this.out_date = out_date;
		this.out_count = out_count;
		this.price = price;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	public int getOut_count() {
		return out_count;
	}

	public void setOut_count(int out_count) {
		this.out_count = out_count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
