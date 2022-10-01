package app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import model.OrderDetails;
import service.CsvFileReader;
import service.IOUtilService;

public class OrderReportGenerator {
	static Logger log = Logger.getLogger(OrderReportGenerator.class.getName());

	private static IOUtilService ioUtilService;

	public static void main(String[] args) {
		ioUtilService = new IOUtilService();

		try {
			File inputFile = ioUtilService.getFileFromUser();
			List<OrderDetails> orderDetails = extractOrderDetails(inputFile);
			ioUtilService.writeAverageQuantityFile(inputFile, orderDetails);
			ioUtilService.writePopularBrandFile(inputFile, orderDetails);

		} catch (Exception e) {
			log.error("Exception occured ", e);
		}

	}

	private static List<OrderDetails> extractOrderDetails(File inputFile) throws IOException {

		try {
			CsvFileReader reader = new CsvFileReader();
			return reader.readFileData(inputFile);
		} catch (IOException e) {
			log.error("Exception occured while reading data from user provded file ", e);
			throw e;
		}
	}

}
