package app;

import service.IOUtilService;

public class OrderReportGenerator {

	public static void main(String[] args) throws Exception {
		IOUtilService ioUtilService = new IOUtilService();
		ioUtilService.startProcess(ioUtilService.getFileFromUser());
	}
}
