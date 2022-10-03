package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

	public void startProcess(File inputFile) throws Exception {
		if (inputFile != null && inputFile.exists()) {
			try {
				List<OrderDetails> orderDetails = extractOrderDetails(inputFile);
				writeAverageQuantityFile(inputFile, orderDetails);
				writePopularBrandFile(inputFile, orderDetails);

			} catch (Exception e) {
				log.error("Exception occured ", e);
				throw e;
			}
		}
	}

	public static List<OrderDetails> extractOrderDetails(File inputFile) throws IOException {
		if (inputFile != null && inputFile.exists()) {
			try {
				CsvFileReader<OrderDetails> reader = new CsvFileReader<OrderDetails>();
				return reader.readFileData(inputFile, OrderDetails.class);
			} catch (IOException e) {
				log.error("Exception occured while reading data from user provded file ", e);
				throw e;
			}
		} else {
			return new ArrayList<OrderDetails>();
		}
	}

	public File getFileFromUser() throws IOException {

		try (Scanner scanner = new Scanner(System.in);) {
			System.out.println("---> Please provide the input CSV file path : ");
			String filePath = scanner.next();
			if (filePath != null && filePath.toString().endsWith(".csv")) {
				File inputFile = new File(filePath);
				if (inputFile != null && inputFile.exists()) {
					return inputFile;
				} else {
					throw new FileNotFoundException("File not found at provided path : " + filePath);
				}
			} else {
				throw new FileNotFoundException(".csv File Not Found/provided");
			}

		} catch (IOException e) {
			log.error("Exception occured while reading user provded file, ", e);
			throw e;
		}
	}

	public static File createFileWithPrefix(String prefix, File inputFile) {

		File createdFile = null;
		if (prefix != null && inputFile != null) {
			String newFileName = prefix + "_" + inputFile.getName();
			String parentFolderPath = inputFile.getParent();
			String newFilePath = parentFolderPath + "\\" + newFileName;
			createdFile = new File(newFilePath);
		}
		return createdFile;
	}

	public void writePopularBrandFile(File inputFile, List<OrderDetails> orderDetails) throws Exception {
		if (orderDetails != null && orderDetails.size() > 0 && inputFile != null) {
			try {
				CsvFileWriter<ProductPopularBrand> writer = new CsvFileWriter<ProductPopularBrand>();
				List<ProductPopularBrand> popularBranddata = processor.createProductPopularBrandData(orderDetails);
				writer.writeDateToFile(popularBranddata, createFileWithPrefix("1", inputFile));
			} catch (IOException e) {
				log.error("Exception occured while writing data to PopularBrandFile ", e);
				throw e;
			}
		}
	}

	public void writeAverageQuantityFile(File inputFile, List<OrderDetails> orderDetails) throws Exception {
		if (orderDetails != null && orderDetails.size() > 0 && inputFile != null) {
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
}
