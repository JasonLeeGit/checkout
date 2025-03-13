package cdl.kata.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import cdl.kata.checkout.order.AddOrderLines;
import cdl.kata.checkout.order.Order;
import cdl.kata.checkout.sku.SKU;
import cdl.kata.checkout.sku.SKUItem;
import cdl.kata.checkout.sku.SetupSKUs;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args)  {
	
		//ogger.isEnabledForLevel(Level.INFO);
		
		logger.info("******************************************************************");
		logger.info("                    CDL Kata Checkout System                      ");
		logger.info("******************************************************************");

		Map<String, SKU> SKUMap = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		Order order = new Order();

		SetupSKUs setupSKUs = new SetupSKUs(scanner, SKUMap);

		SKUMap = setupSKUs.addSKUs();
	
		AddOrderLines addOrderLines = new AddOrderLines(order);

		SKUItem skuItem = new SKUItem(scanner, SKUMap, addOrderLines);
		skuItem.addSKUItems();
	
		logger.info("******************************************************************");
		logger.info("Final Order Total = £" + order.printFinalOrderTotal());
		logger.info("******************************************************************");

		scanner.close();
	}
}
