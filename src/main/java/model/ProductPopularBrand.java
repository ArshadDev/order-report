package model;

import com.opencsv.bean.CsvBindByPosition;

public class ProductPopularBrand {

	@CsvBindByPosition(position = 0)
	private String productName;
	@CsvBindByPosition(position = 1)
	private String brand;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
