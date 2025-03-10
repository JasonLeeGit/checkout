package cdl.kata.checkout.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdl.kata.checkout.sku.SKU;

public class Order {

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	private Map<String, SKU> SKUs = new HashMap<String, SKU>();
	private BigDecimal orderTotal;

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Map<String, SKU> getSKUs() {
		return SKUs;
	}

	public void setSKUs(Map<String, SKU> sKUs) {
		this.SKUs = sKUs;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}	

	public BigDecimal printFinalOrderTotal() {
		return getOrderLines().stream().map(ol -> ol.getOrderlineTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		return "Order [orderLines=" + orderLines + ", SKUs=" + SKUs + ", orderTotal=" + orderTotal + "]";
	}
}
