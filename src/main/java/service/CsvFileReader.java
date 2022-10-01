package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.bean.CsvToBeanBuilder;

import model.OrderDetails;

public class CsvFileReader implements CustomFileReader {
	static Logger log = Logger.getLogger(CsvFileReader.class.getName());

	public List<OrderDetails> readFileData(File fileToRead) throws IOException {

		try (FileReader fileReader = new FileReader(fileToRead);) {
			List<OrderDetails> orderDetails = new CsvToBeanBuilder<OrderDetails>(fileReader)
					.withType(OrderDetails.class).build().parse();

			log.info("Read " + (orderDetails != null ? orderDetails.size() : 0) + " records from user provided file ("
					+ fileToRead.getAbsolutePath() + ") ");

			return orderDetails;
		} catch (Exception e) {
			log.error("Exception occured while reading data from file (" + fileToRead.getAbsolutePath() + ") ", e);
			throw e;
		}
	}

}
