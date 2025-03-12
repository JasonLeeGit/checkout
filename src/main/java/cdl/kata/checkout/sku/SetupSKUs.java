package cdl.kata.checkout.sku;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SetupSKUs {

	private Map<String, SKU> SKUMap = new HashMap<>();
	private boolean enterSKU = true;
	private Scanner scanner;
	private static final String DECIMAL_ERROR = "Incorrect Value Please enter upper case letter for SKU item you entered: ";
	private static final String UPPERCASE_ERROR = "Incorrect Value Please enter upper case letter for SKU item you entered: ";
	private static final String INTEGER_ERROR = "Incorrect Value Please enter a integer value for quantity you entered: ";
	private static final String NEW_SKU_MESSAGE = "Enter a new SKU Item press 'Y' to 'CONTINUE' to 'END' press 'N'";
	private static final String INPUT_MESSAGE = "Enter Sku Item, Price, Quantity For SpecialPrice, Special Price separated by a space: A 2.00 6 10.00";
	private static final String FORMAT_MESSAGE = "Please use the following example format: A 5.00 10 45.00";

	public SetupSKUs(Scanner scanner, Map<String, SKU> sKUMap) {
		super();
		this.scanner = scanner;
		this.SKUMap = sKUMap;
	}

	public Map<String, SKU> addSKUs() {
		while (enterSKU == true) {

			System.out.println(INPUT_MESSAGE);

			String item = null;
			BigDecimal price = null;
			int quantity = 0;
			BigDecimal specialPrice = null;

			if (scanner.hasNext(Pattern.compile("[A-Za-z]"))) {
				item = scanner.next(Pattern.compile("[A-Za-z]"));
			} else {
				printValidationError(UPPERCASE_ERROR + scanner.next());
			}

			if (scanner.hasNextBigDecimal()) {
				price = scanner.nextBigDecimal();
			} else {
				printValidationError(DECIMAL_ERROR + scanner.next());
			}

			if (scanner.hasNextInt()) {
				quantity = scanner.nextInt();
			} else {
				printValidationError(INTEGER_ERROR + scanner.next());
			}

			if (scanner.hasNextBigDecimal()) {
				specialPrice = scanner.nextBigDecimal();
			} else {
				printValidationError(DECIMAL_ERROR + scanner.next());
			}

			if (valid(item, price, quantity, specialPrice)) {
				addSKU(item.toUpperCase(), price, quantity, specialPrice);
			}
			System.out.println(NEW_SKU_MESSAGE);

			try {
				String addAnother = scanner.next();
				if (addAnother.equalsIgnoreCase("N")) {
					enterSKU = false;
				} else if (addAnother.equalsIgnoreCase("Y")) {
					System.out.println(NEW_SKU_MESSAGE);
				} else {
					scanner.nextLine();
					throw new Exception("Error incorrect letter added \n");
				}
			} catch (Exception e) {
				System.out.println("Error incorrect letter added");
			}
		}
		return SKUMap;
	}

	public void addSKU(String item, BigDecimal price, int quantity, BigDecimal specialPrice) {
		SKUMap.put(item, new SKU(item, price, quantity, specialPrice));
	}

	private boolean valid(String item, BigDecimal price, int quantity, BigDecimal specialPrice) {
		if (item != null && price != null && quantity > 0 && specialPrice != null) {
			return true;
		} else {
			return false;
		}
	}

	private void printValidationError(String errorMessage) {
		System.out.println(errorMessage);
		System.out.println(FORMAT_MESSAGE);
	}
}
