package cdl.kata.checkout.order;

import java.math.BigDecimal;

import cdl.kata.checkout.sku.SKU;

public class OrderLine {

	private SKU sku;
	private int skuQuantity;
	private BigDecimal orderlineTotal;

	public OrderLine(SKU sku, int suyQuantity, BigDecimal orderlineTotal) {
		super();
		this.sku = sku;
		this.skuQuantity = suyQuantity;
		this.orderlineTotal = orderlineTotal;
	}

	public SKU getSku() {
		return sku;
	}

	public void setSku(SKU sku) {
		this.sku = sku;
	}

	public int getSkuQuantity() {
		return skuQuantity;
	}

	public void setSkuQuantity(int skuQuantity) {
		this.skuQuantity = skuQuantity;
	}

	public BigDecimal getOrderlineTotal() {
		return orderlineTotal;
	}

	public void setOrderlineTotal(BigDecimal orderlineTotal) {
		this.orderlineTotal = orderlineTotal;
	}
}
