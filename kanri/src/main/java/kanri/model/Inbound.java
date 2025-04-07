package kanri.model;

import java.util.Date;

public class Inbound {
	private String client_id;
	private String product_id;
	private Date in_date;
	private int in_count;
	private int price;

	public Inbound() {
		// TODO Auto-generated constructor stub
	}

	public Inbound(String client_id, String product_id, Date in_date, int in_count, int price) {
		super();
		this.client_id = client_id;
		this.product_id = product_id;
		this.in_date = in_date;
		this.in_count = in_count;
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

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public int getIn_count() {
		return in_count;
	}

	public void setIn_count(int in_count) {
		this.in_count = in_count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
