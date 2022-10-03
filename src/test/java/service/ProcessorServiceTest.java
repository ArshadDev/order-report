package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.OrderDetails;
import model.ProductAverageQuantity;
import model.ProductPopularBrand;

public class ProcessorServiceTest {

	private static final ProcessorService processor = new ProcessorService();
	private static File actualinputFile;

	@Before
	public void setUp() {
		actualinputFile = new File("src/test/resources/sample-input.csv");
	}

	@Test
	public void testCreateProductAverageQuantityData() {
		try {
			List<OrderDetails> orderDetails = IOUtilService.extractOrderDetails(actualinputFile);
			assertNotNull(orderDetails);
			assertEquals(5, orderDetails.size());

			List<ProductAverageQuantity> data = processor.createProductAverageQuantityData(orderDetails);
			assertNotNull(data);
			assertEquals(2, data.size());

			data.stream().forEach(d -> {
				if (d.getProductName() == "Intelligent Copper Knife") {
					assertEquals(2.4, d.getAverageQuantity());
				}
				if (d.getProductName() == "Small Granite Shoes") {
					assertEquals(0.8, d.getAverageQuantity());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateProductPopularBrandData() {
		try {
			List<OrderDetails> orderDetails = IOUtilService.extractOrderDetails(actualinputFile);
			assertNotNull(orderDetails);
			assertEquals(5, orderDetails.size());

			List<ProductPopularBrand> data = processor.createProductPopularBrandData(orderDetails);
			assertNotNull(data);
			assertEquals(2, data.size());

			data.stream().forEach(d -> {
				if (d.getProductName() == "Intelligent Copper Knife") {
					assertEquals("Hilll-Gorczany", d.getBrand());
				}
				if (d.getProductName() == "Small Granite Shoes") {
					assertEquals("Rowe and Legros", d.getBrand());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
