package cdl.kata.checkout.sku;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetupSKUsTest {
	
	private SetupSKUs classUnderTest;
	private Map<String, SKU> SKUMap; 
	private Map<String, SKU> addSkusResults;
	
	@Mock
	private Scanner mockScanner;
	
	@BeforeEach
	private void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);	
		SKUMap = new HashMap<>();	
		classUnderTest = new SetupSKUs(mockScanner, SKUMap);
	}
	
	@Test
	public void addValidSKUsInputTest() {
		
		when(mockScanner.hasNext()).thenReturn(true); 
		when(mockScanner.next()).thenReturn("A", "Y", "B","N");
		
		when(mockScanner.hasNextBigDecimal()).thenReturn(true);
		when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("2.00"), new BigDecimal("10.00"), new BigDecimal("3.00"), new BigDecimal("28.00")); 
		
		when(mockScanner.hasNextInt()).thenReturn(true);		
		when(mockScanner.nextInt()).thenReturn(3, 4); 
		
		addSkusResults = classUnderTest.addSKUs();
	
		assertTrue(addSkusResults.size() == 2);
		
		assertTrue(addSkusResults.get("A").getItem().equals("A"));
		assertTrue(addSkusResults.get("A").getPrice().equals(new BigDecimal("2.00")));
		assertTrue(addSkusResults.get("A").getQuantityForSpecialPrice() == 3);
		assertTrue(addSkusResults.get("A").getSpecialPrice().equals(new BigDecimal("10.00")));
		
		assertTrue(addSkusResults.get("B").getItem().equals("B"));
		assertTrue(addSkusResults.get("B").getPrice().equals(new BigDecimal("3.00")));
		assertTrue(addSkusResults.get("B").getQuantityForSpecialPrice() == 4);
		assertTrue(addSkusResults.get("B").getSpecialPrice().equals(new BigDecimal("28.00")));
	}
}
