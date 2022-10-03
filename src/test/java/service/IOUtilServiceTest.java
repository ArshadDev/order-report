package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import model.OrderDetails;
import model.ProductAverageQuantity;
import model.ProductPopularBrand;

public class IOUtilServiceTest {

	private static final IOUtilService service = new IOUtilService();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();;
	private static File actualinputFile;

	@Before
	public void setUp() {
		actualinputFile = new File("src/test/resources/sample-input.csv");
	}

	@Test
	public void testExtractOrderDetails() {
		try {
			List<OrderDetails> orderDetails = IOUtilService.extractOrderDetails(actualinputFile);
			assertNotNull(orderDetails);
			assertEquals(5, orderDetails.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateFileWithPrefix() {

		try {
			File inputFile = temporaryFolder.newFolder("TestFolder").toPath().resolve("input.txt").toFile();
			File newFile = IOUtilService.createFileWithPrefix("1", inputFile);
			assertNotNull(newFile);
			assertEquals("1_input.txt", newFile.getName());
			assertEquals(inputFile.getParent(), newFile.getParent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWritePopularBrandFile() {

		try {
			// Reading actual data from input file
			List<OrderDetails> orderDetails = IOUtilService.extractOrderDetails(actualinputFile);

			// Creating temp file location to write output file to
			File tempInputFile = temporaryFolder.newFolder("TestFolder").toPath().resolve("output.csv").toFile();
			service.writePopularBrandFile(tempInputFile, orderDetails);

			// Reading data from output file created in temp location
			String outputFile = tempInputFile.getParent() + "\\" + "1_output.csv";
			CsvFileReader<ProductPopularBrand> reader = new CsvFileReader<ProductPopularBrand>();
			List<ProductPopularBrand> readFileData = reader.readFileData(new File(outputFile),
					ProductPopularBrand.class);

			// Verifying the results
			assertNotNull(readFileData);
			assertEquals(2, readFileData.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testWriteAverageQuantityFile() {

		try {
			// Reading actual data from input file
			List<OrderDetails> orderDetails = IOUtilService.extractOrderDetails(actualinputFile);

			// Creating temp file location to write output file to
			File tempInputFile = temporaryFolder.newFolder("TestFolder").toPath().resolve("output.csv").toFile();
			service.writeAverageQuantityFile(tempInputFile, orderDetails);

			// Reading data from output file created in temp location
			String outputFile = tempInputFile.getParent() + "\\" + "0_output.csv";
			CsvFileReader<ProductAverageQuantity> reader = new CsvFileReader<ProductAverageQuantity>();
			List<ProductAverageQuantity> readFileData = reader.readFileData(new File(outputFile),
					ProductAverageQuantity.class);

			// Verifying the results
			assertNotNull(readFileData);
			assertEquals(2, readFileData.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
