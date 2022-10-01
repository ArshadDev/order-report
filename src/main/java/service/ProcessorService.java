package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.OrderDetails;
import model.ProductAverageQuantity;
import model.ProductPopularBrand;

public class ProcessorService {

	public List<ProductAverageQuantity> createProductAverageQuantityData(List<OrderDetails> orderDetails) {

		List<ProductAverageQuantity> finalData = new ArrayList<>();

		if (orderDetails != null && !orderDetails.isEmpty()) {
			long totalOrders = orderDetails.size();

			Map<String, Long> productToQuantityMap = orderDetails.stream().collect(Collectors
					.groupingBy(order -> order.getProductName(), Collectors.summingLong(order -> order.getQuantity())));

			productToQuantityMap.entrySet().forEach(entry -> {
				ProductAverageQuantity paq = new ProductAverageQuantity();
				paq.setProductName(entry.getKey());
				paq.setAverageQuantity(String.valueOf(getAverage(entry.getValue(), totalOrders)));
				finalData.add(paq);
			});
		}
		return finalData;
	}

	public List<ProductPopularBrand> createProductPopularBrandData(List<OrderDetails> orderDetails) {

		List<ProductPopularBrand> finalData = new ArrayList<>();
		if (orderDetails != null && !orderDetails.isEmpty()) {

			Map<String, List<OrderDetails>> productToOrdersMap = orderDetails.stream()
					.collect(Collectors.groupingBy(order -> order.getProductName()));

			productToOrdersMap.entrySet().forEach(entry -> {
				ProductPopularBrand ppb = new ProductPopularBrand();
				ppb.setProductName(entry.getKey());
				ppb.setBrand(getPopularBrandName(entry.getValue()));
				finalData.add(ppb);
			});
		}
		return finalData;

	}

	private static double getAverage(long productCount, long totalCount) {
		double average = 0.0;

		if (totalCount != 0) {
			average = (double) productCount / (double) totalCount;
		}
		return average;
	}

	private static String getPopularBrandName(List<OrderDetails> orders) {
		String brandName = null;
		if (orders != null && !orders.isEmpty()) {

			Map<String, Long> brandToOrdersCountMap = orders.stream()
					.collect(Collectors.groupingBy(order -> order.getBrandName(), Collectors.counting()));

			long highestOrderCount = 0;

			for (Entry<String, Long> entry : brandToOrdersCountMap.entrySet()) {
				if (entry.getValue() > highestOrderCount) {
					highestOrderCount = entry.getValue();
					brandName = entry.getKey();
				}
			}
		}
		return brandName;
	}
}
