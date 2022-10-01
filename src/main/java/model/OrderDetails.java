package model;

import com.opencsv.bean.CsvBindByPosition;

public class OrderDetails {

	@CsvBindByPosition(position = 0)
	private String orderId;
	@CsvBindByPosition(position = 1)
	private String area;
	@CsvBindByPosition(position = 2)
	private String productName;
	@CsvBindByPosition(position = 3)
	private long quantity;
	@CsvBindByPosition(position = 4)
	private String brandName;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
