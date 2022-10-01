package service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CsvFileWriter<T> implements CustomFileWriter<T> {
	static Logger log = Logger.getLogger(CsvFileWriter.class.getName());

	@Override
	public void writeDateToFile(List<T> data, File fileToWrite) throws Exception {

		try (FileWriter writer = new FileWriter(fileToWrite);) {
			
			StatefulBeanToCsv<T> csvWriter = new StatefulBeanToCsvBuilder<T>(writer).withApplyQuotesToAll(false).build();
			csvWriter.write(data);

			log.error("Wrote " + (data.size()) + " records to file (" + fileToWrite.getAbsolutePath() + ") ");

		} catch (Exception e) {
			log.error("Exception occured while writing data to file (" + fileToWrite.getAbsolutePath() + ") ", e);
			throw e;
		}

	}

}
