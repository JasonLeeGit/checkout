package cdl.kata.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cdl.kata.checkout.order.AddOrderLines;
import cdl.kata.checkout.order.Order;
import cdl.kata.checkout.sku.SKU;
import cdl.kata.checkout.sku.SKUItem;
import cdl.kata.checkout.sku.SetupSKUs;

public class Main {

	public static void main(String[] args)  {
		System.out.println("******************************************************************");
		System.out.println("                    CDL Kata Checkout System                      ");
		System.out.println("******************************************************************");

		Map<String, SKU> SKUMap = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		Order order = new Order();

		SetupSKUs setupSKUs = new SetupSKUs(scanner, SKUMap);

		SKUMap = setupSKUs.addSKUs();
	
		AddOrderLines addOrderLines = new AddOrderLines(order);

		SKUItem skuItem = new SKUItem(scanner, SKUMap, addOrderLines);
		skuItem.addSKUItems();
	
		System.out.println("******************************************************************");
		System.out.println("Final Order Total = £" + order.printFinalOrderTotal());
		System.out.println("******************************************************************");

		scanner.close();
	}
}
