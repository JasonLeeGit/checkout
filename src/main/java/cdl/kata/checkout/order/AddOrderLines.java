package cdl.kata.checkout.order;

import java.math.BigDecimal;
import java.math.BigInteger;

import cdl.kata.checkout.sku.SKU;

public class AddOrderLines {

	private BigDecimal cost = new BigDecimal(BigInteger.ZERO, 2);
	private BigDecimal normalPrice = new BigDecimal(BigInteger.ZERO, 2);
	private BigDecimal discountPrice = new BigDecimal(BigInteger.ZERO, 2);
	private Order order;

	public AddOrderLines(Order order) {
		super();
		this.order = order;
	}

	public void calculate(int quantity, SKU skuPrice) {
		if (order.getOrderLines().size() > 0) {
			boolean matched = false;
			for (int i = 0; i < order.getOrderLines().size(); i++) {
				// do we have any existing orderlines for SKU
				OrderLine orderLine = order.getOrderLines().get(i);
				if (orderLine != null) {
					if (orderLine.getSku().getItem().equalsIgnoreCase(skuPrice.getItem())) {

						orderLine.setSkuQuantity(orderLine.getSkuQuantity() + quantity);

						int skuQty = orderLine.getSkuQuantity();
						int singlePriceRemainder = skuQty % skuPrice.getQuantityForSpecialPrice();

						calulatePrices(skuPrice, skuQty, singlePriceRemainder);

						orderLine.setOrderlineTotal(normalPrice.add(cost));
						System.out.println("******************************************************************");
						System.out.println("SKU " + skuPrice.getItem() + " Order Total = £" + normalPrice.add(cost));
						System.out.println("******************************************************************");
						matched = true;
						break;
					}
				}
			}
			if (!matched) {
				// no existing sku order line found so add new sku order line
				int singlePriceRemainder = quantity % skuPrice.getQuantityForSpecialPrice();

				calulatePrices(skuPrice, quantity, singlePriceRemainder);
				order.getOrderLines().add(new OrderLine(skuPrice, quantity, normalPrice.add(cost)));
				System.out.println("******************************************************************");
				System.out.println("SKU " + skuPrice.getItem() + " Order Total = £" + normalPrice.add(cost));
				System.out.println("******************************************************************");
			}
		} else {
			// first ever order lne
			int singlePriceRemainder = quantity % skuPrice.getQuantityForSpecialPrice();

			calulatePrices(skuPrice, quantity, singlePriceRemainder);
			order.getOrderLines().add(new OrderLine(skuPrice, quantity, normalPrice.add(cost)));
			System.out.println("******************************************************************");
			System.out.println("SKU " + skuPrice.getItem() + " Order Total = £" + normalPrice.add(cost));
			System.out.println("******************************************************************");
		}
		System.out.println("Running Order Total = £" + order.printFinalOrderTotal());
		System.out.println("******************************************************************");
	}

	private void calulatePrices(SKU skuPrice, int skuQty, int singlePriceRemainder) {
		normalPrice = calculateCost(singlePriceRemainder, skuPrice.getPrice());
		discountPrice = calculateCost(skuQty - singlePriceRemainder, skuPrice.getSpecialPrice());
		cost = discountPrice.divide(new BigDecimal(skuPrice.getQuantityForSpecialPrice()));
	}

	private BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
		return itemPrice.multiply(new BigDecimal(itemQuantity));
	}
}
