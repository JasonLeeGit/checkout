package cdl.kata.checkout.sku;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SetupSKUs {

	private Scanner scanner;
	private Map<String, SKU> SKUMap;
	
	private static final String DECIMAL_ERROR = "Incorrect Value Please enter a decimal value you entered: ";
	private static final String LETTER_ERROR = "Incorrect Value Please enter letter for SKU item you entered: ";
	private static final String INTEGER_ERROR = "Incorrect Value Please enter a integer value for quantity you entered: ";
	private static final String NEW_SKU_MESSAGE = "Enter a new SKU Item press 'Y' to 'CONTINUE' to 'END' press 'N'";
	private static final String INPUT_MESSAGE = "Enter Sku Item, Price, Quantity For SpecialPrice, Special Price separated by a space: A 2.00 6 10.00";
	private static final String FORMAT_MESSAGE = "Please use the following example format: A 5.00 10 45.00";

	private static Logger logger = LoggerFactory.getLogger(SetupSKUs.class);
	
	public Map<String, SKU> addSKUs() {
		boolean enterSKU = true;
		while (enterSKU) {

			logger.info(INPUT_MESSAGE);

			String item = null;
			BigDecimal price = null;
			int quantity = 0;
			BigDecimal specialPrice = null;
			
			if (scanner.hasNext()) {
				item = scanner.next();
			} else {
				printValidationError(LETTER_ERROR + scanner.next());
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

			if (validateUserInputs(item, price, quantity, specialPrice)) {
				addSKU(item.toUpperCase(), price, quantity, specialPrice);
			}
			logger.info(NEW_SKU_MESSAGE);

			try {
				String addAnother = scanner.next();
				if (addAnother.equalsIgnoreCase("N")) {
					enterSKU = false;
				} else if (!addAnother.equalsIgnoreCase("Y")) {
					scanner.nextLine();
					throw new Exception("Error incorrect letter added");
				}
			} catch (Exception e) {
				logger.info("Error incorrect letter added");
			}
		}
		return SKUMap;
	}

	public void addSKU(String item, BigDecimal price, int quantity, BigDecimal specialPrice) {
		SKUMap.put(item, new SKU(item, price, quantity, specialPrice));
	}

	private boolean validateUserInputs(String item, BigDecimal price, int quantity, BigDecimal specialPrice) {
		if (item != null && price != null && quantity > 0 && specialPrice != null) {
			return true;
		} else {
			return false;
		}
	}

	private void printValidationError(String errorMessage) {
		logger.info(errorMessage);
		logger.info(FORMAT_MESSAGE);
	}
}
