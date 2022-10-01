package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import model.OrderDetails;
import model.ProductAverageQuantity;
import model.ProductPopularBrand;

public class IOUtilService {
	static Logger log = Logger.getLogger(IOUtilService.class.getName());

	private static ProcessorService processor;

	public IOUtilService() {
		processor = new ProcessorService();
	}

	public File getFileFromUser() throws IOException {

		try (Scanner scanner = new Scanner(System.in);) {
			System.out.println("---> Please provide the input CSV file path : ");
			String filePath = scanner.next();
			File inputFile = new File(filePath);
			if (inputFile != null && inputFile.exists()) {
				return inputFile;
			} else {
				throw new FileNotFoundException("File not found at provided path : " + filePath);
			}
		} catch (IOException e) {
			log.error("Exception occured while reading user provded file, ", e);
			throw e;
		}
	}

	public File createFileWithPrefix(String prefix, File inputFile) {
		String newFileName = prefix + "_" + inputFile.getName();
		String newFilePath = inputFile.getAbsolutePath().replace(inputFile.getName(), newFileName);
		File newFile = new File(newFilePath);
		return newFile;
	}

	public void writePopularBrandFile(File inputFile, List<OrderDetails> orderDetails) throws Exception {
		try {
			CsvFileWriter<ProductPopularBrand> writer = new CsvFileWriter<ProductPopularBrand>();
			List<ProductPopularBrand> popularBranddata = processor.createProductPopularBrandData(orderDetails);
			writer.writeDateToFile(popularBranddata, createFileWithPrefix("1", inputFile));
		} catch (IOException e) {
			log.error("Exception occured while writing data to PopularBrandFile ", e);
			throw e;
		}
	}

	public void writeAverageQuantityFile(File inputFile, List<OrderDetails> orderDetails) throws Exception {
		try {
			CsvFileWriter<ProductAverageQuantity> writer = new CsvFileWriter<ProductAverageQuantity>();
			List<ProductAverageQuantity> averageQtyData = processor.createProductAverageQuantityData(orderDetails);
			writer.writeDateToFile(averageQtyData, createFileWithPrefix("0", inputFile));
		} catch (IOException e) {
			log.error("Exception occured while writing data to AverageQuantityFile ", e);
			throw e;
		}
	}
}
