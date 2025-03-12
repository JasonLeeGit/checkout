package cdl.kata.checkout.sku;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import cdl.kata.checkout.order.AddOrderLines;

public class SKUItem {

	private boolean enterOrder = true;
	private Scanner scanner;
	private Map<String, SKU> SKUMap;
	private AddOrderLines addOrderLines;
	private static final String UPPERCASE_ERROR = "Incorrect Value Please enter upper case letter for SKU item you entered: ";
	private static final String INTEGER_ERROR = "Incorrect Value Please enter a integer value for quantity you entered: ";
	private static final String NEW_SKU_MESSAGE = "Enter a new SKU Item press 'Y' to 'CONTINUE' to 'END' press 'N'";;
	private static final String INPUT_MESSAGE = "Enter Sku Item and Quantity seperated by a space IE: A 5";
	private static final String ERROR_MESSAGE_START = "Incorrect Values entered for item ";
	private static final String ERROR_MESSAGE_MID = " or quantity ";

	public SKUItem(Scanner scanner, Map<String, SKU> sKUMap, AddOrderLines addOrderLines) {
		super();
		this.scanner = scanner;
		this.SKUMap = sKUMap;
		this.addOrderLines = addOrderLines;
	}

	public void addSKUItems() throws Exception {

		while (enterOrder == true) {

			System.out.println(INPUT_MESSAGE);

			String item = "";
			if (scanner.hasNext(Pattern.compile("[A-Za-z]"))) {
				item = scanner.next(Pattern.compile("[A-Za-z]"));
			} else {
				printErrorMessage(UPPERCASE_ERROR + scanner.next());
			}

			int quantity = 0;
			if (scanner.hasNextInt()) {
				quantity = scanner.nextInt();
			} else {
				printErrorMessage(INTEGER_ERROR + scanner.next());
			}

			SKU skuPrice = SKUMap.get(item.toUpperCase());

			if (quantity > 0 && skuPrice != null) {
				addOrderLines.calculate(quantity, skuPrice);
				System.out.println(NEW_SKU_MESSAGE);

//				String addAnother = scanner.next();
//				if (addAnother.equalsIgnoreCase("N")) {
//					enterOrder = false;
//				} else {
//					scanner.nextLine();
//				}
				
				String addAnother = scanner.next();
				if (addAnother.equalsIgnoreCase("N")) {
					enterOrder = false;
				} else  if (addAnother.equalsIgnoreCase("Y")) {
					System.out.println(NEW_SKU_MESSAGE);
				} else {
					throw new Exception("Error incorrect letter added");
				}
			} else {
				printErrorMessage(ERROR_MESSAGE_START + item + ERROR_MESSAGE_MID + quantity);
			}
		}
	}

	private void printErrorMessage(String errorMessage) {
		System.out.println(errorMessage);
	}
}
