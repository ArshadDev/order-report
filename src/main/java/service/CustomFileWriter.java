package service;

import java.io.File;
import java.util.List;

public interface CustomFileWriter<T> {

	void writeDateToFile(List<T> data, File fileToWrite) throws Exception;

}
