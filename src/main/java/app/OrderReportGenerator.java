package app;

import java.io.IOException;

import service.IOUtilService;

public class OrderReportGenerator {

	public static void main(String[] args) throws IOException {
		IOUtilService ioUtilService = new IOUtilService();
		ioUtilService.startProcess(ioUtilService.getFileFromUser());
	}
}
