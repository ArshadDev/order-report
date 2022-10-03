package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CustomFileReader<T> {

	List<T> readFileData(File fileToRead, Class<T> type) throws IOException;

}
