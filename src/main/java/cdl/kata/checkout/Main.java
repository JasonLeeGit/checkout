package cdl.kata.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import cdl.kata.checkout.order.AddOrderLines;
import cdl.kata.checkout.order.Order;
import cdl.kata.checkout.sku.SKU;
import cdl.kata.checkout.sku.SKUItem;
import cdl.kata.checkout.sku.SetupSKUs;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		logger.info("******************************************************************");
		logger.info("                    CDL Kata Checkout System                      ");
		logger.info("******************************************************************");

		try {
			Map<String, SKU> SKUMap = new HashMap<>();
			
			Scanner scanner = new Scanner(System.in);
			
			Order order = new Order();
	
			SetupSKUs setupSKUs = new SetupSKUs(scanner, SKUMap);
	
			SKUMap = setupSKUs.addSKUs();
	
			SKUItem skuItem = new SKUItem(scanner, SKUMap, new AddOrderLines(order));
			skuItem.addSKUItems();
	
			logger.info("******************************************************************");
			logger.info("Final Order Total = £" + order.printFinalOrderTotal());
			logger.info("******************************************************************");
		
		} catch(Exception e) {
			logger.info(e.toString());
		}
		
	}
}
