package model;

import com.opencsv.bean.CsvBindByPosition;

public class ProductAverageQuantity {

	@CsvBindByPosition(position = 0)
	private String productName;
	@CsvBindByPosition(position = 1)
	private String averageQuantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAverageQuantity() {
		return averageQuantity;
	}

	public void setAverageQuantity(String averageQuantity) {
		this.averageQuantity = averageQuantity;
	}

}
