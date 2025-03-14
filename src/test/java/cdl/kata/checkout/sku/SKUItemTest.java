package cdl.kata.checkout.sku;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cdl.kata.checkout.order.AddOrderLines;
import cdl.kata.checkout.order.Order;

public class SKUItemTest {
	
	private SKUItem classUnderTest;
	private Map<String, SKU> SKUMap = new HashMap<>();
	private Order order = new Order();
	private AddOrderLines addOrderLines;
	
	@Mock
	private Scanner mockScanner;
	
	@BeforeEach
	private void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);	
		addOrderLines = new AddOrderLines(order);
		SKUMap = setupSKUs();
		classUnderTest = new SKUItem(mockScanner, SKUMap, addOrderLines);
	}
	
	@Test
	public void addSKUItems() {
		
		when(mockScanner.hasNext()).thenReturn(true);
		when(mockScanner.next()).thenReturn("A", "Y", "B", "Y", "A", "Y", "C", "Y", "B", "N" );
			
		when(mockScanner.hasNextInt()).thenReturn(true);		
		when(mockScanner.nextInt()).thenReturn(1, 1, 5, 1, 1); 
		
		classUnderTest.addSKUItems();
		
		assert(order.printFinalOrderTotal().equals(new BigDecimal("17.17")));		
	}


	private Map<String, SKU> setupSKUs() {
		Map<String, SKU> map = new HashMap<String, SKU>();
		map.put("A", new SKU("A", new BigDecimal("2.00"), 6, new BigDecimal("10.00")));
		map.put("B", new SKU("B", new BigDecimal("3.00"), 10, new BigDecimal("28.00")));
		map.put("C", new SKU("C", new BigDecimal("1.17"), 10, new BigDecimal("10.70")));
		
		return map;	
	}
}
