package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.OrderDetails;

public interface CustomFileReader {

	List<OrderDetails> readFileData(File fileToRead) throws IOException;

}
