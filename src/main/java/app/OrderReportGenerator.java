package app;

import service.IOUtilService;

public class OrderReportGenerator {

	public static void main(String[] args) {
		IOUtilService ioUtilService = new IOUtilService();
		ioUtilService.startProcess();
	}
}
