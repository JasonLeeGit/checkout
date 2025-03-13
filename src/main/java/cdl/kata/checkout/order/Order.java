package cdl.kata.checkout.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdl.kata.checkout.sku.SKU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	private Map<String, SKU> SKUs = new HashMap<String, SKU>();
	private BigDecimal orderTotal;	

	public BigDecimal printFinalOrderTotal() {
		return getOrderLines().stream().map(ol -> ol.getOrderlineTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
