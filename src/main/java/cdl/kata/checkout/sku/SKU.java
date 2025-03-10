package cdl.kata.checkout.sku;

import java.math.BigDecimal;

public class SKU {

	private String item;
	private BigDecimal price;
	private int quantityForSpecialPrice;
	private BigDecimal specialPrice;

	public SKU(String item, BigDecimal price, int quantityForSpecialPrice, BigDecimal specialPrice) {
		super();
		this.item = item;
		this.price = price;
		this.quantityForSpecialPrice = quantityForSpecialPrice;
		this.specialPrice = specialPrice;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantityForSpecialPrice() {
		return quantityForSpecialPrice;
	}

	public void setQuantityForSpecialPrice(int quantityForSpecialPrice) {
		this.quantityForSpecialPrice = quantityForSpecialPrice;
	}

	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public String toString() {
		return "SKU [item=" + item + ", price=" + price + ", quantityForSpecialPrice=" + quantityForSpecialPrice
				+ ", specialPrice=" + specialPrice + "]";
	}

}
