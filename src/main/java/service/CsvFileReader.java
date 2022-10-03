package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.bean.CsvToBeanBuilder;

public class CsvFileReader<T> implements CustomFileReader<T> {
	static Logger log = Logger.getLogger(CsvFileReader.class.getName());

	

	public List<T> readFileData(File fileToRead, Class<T> type) throws IOException {

		try (FileReader fileReader = new FileReader(fileToRead);) {			
			List<T> data = new CsvToBeanBuilder<T>(fileReader).withType(type).build().parse();

			log.info("Read " + (data != null ? data.size() : 0) + " records from user provided file ("
					+ fileToRead.getAbsolutePath() + ") ");

			return data;
		} catch (Exception e) {
			log.error("Exception occured while reading data from file (" + fileToRead.getAbsolutePath() + ") ", e);
			throw e;
		}
	}

}
