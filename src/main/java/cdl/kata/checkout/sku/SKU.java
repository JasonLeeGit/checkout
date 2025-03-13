package cdl.kata.checkout.sku;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SKU {

	private String item;
	private BigDecimal price;
	private int quantityForSpecialPrice;
	private BigDecimal specialPrice;

}
