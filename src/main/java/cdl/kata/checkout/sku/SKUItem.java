package cdl.kata.checkout.sku;

import java.util.Map;
import java.util.Scanner;

import cdl.kata.checkout.order.AddOrderLines;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SKUItem {

	private Scanner scanner;
	private Map<String, SKU> SKUMap;
	private AddOrderLines addOrderLines;
	private static final String LETTER_ERROR = "Incorrect Value Please enter letter for SKU item you entered: ";
	private static final String INTEGER_ERROR = "Incorrect Value Please enter a integer value for quantity you entered: ";
	private static final String NEW_SKU_MESSAGE = "Enter a new SKU Item press 'Y' to 'CONTINUE' to 'END' press 'N'";;
	private static final String INPUT_MESSAGE = "Enter Sku Item and Quantity seperated by a space IE: A 5";
	private static final String ERROR_MESSAGE_START = "Incorrect Values entered for item ";
	private static final String ERROR_MESSAGE_MID = " or quantity ";

	private static final Logger logger = Logger.getLogger(SKUItem.class.getName());
	
	public void addSKUItems() {
		boolean enterOrder = true;	
		while (enterOrder) {

			logger.info(INPUT_MESSAGE);

			String item = "";
			if (scanner.hasNext()) { 
				item = scanner.next();
			} else {
				printErrorMessage(LETTER_ERROR + scanner.next());
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
				logger.info(NEW_SKU_MESSAGE);

				try {
					if (scanner.hasNext()) { 
						String addAnother = scanner.next();
						if (addAnother.equalsIgnoreCase("N")) {
							enterOrder = false;
						} else if (addAnother.equalsIgnoreCase("Y")) {
							logger.info(NEW_SKU_MESSAGE);
						} else {
							scanner.nextLine();
							throw new Exception("Error incorrect letter added");
						}
					}
				} catch (Exception e) {
					logger.info("Error incorrect letter added");
				}
			} else {
				printErrorMessage(ERROR_MESSAGE_START + item + ERROR_MESSAGE_MID + quantity);
			}
		}
	}

	private void printErrorMessage(String errorMessage) {
		logger.info(errorMessage);
	}
}
