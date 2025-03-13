package cdl.kata.checkout.order;

import java.math.BigDecimal;

import cdl.kata.checkout.sku.SKU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

	private SKU sku;
	private int skuQuantity;
	private BigDecimal orderlineTotal;

}
