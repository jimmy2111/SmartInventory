package com.einfochips.smartinventory.servicetests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.EmailScheduler;
import com.einfochips.smartinventory.service.EmailService;
import com.einfochips.smartinventory.service.ProductService;

@SpringBootTest
public class EmailSchedulerTest {

	@InjectMocks
	private EmailScheduler emailScheduler;
	@Mock
	private EmailService emailService;
	@Mock
	private ProductService productService;
	
	@Test
	public void checkProductQuantityTest() {
		String to = "example@example.com";
		String subject= "Below Threshold Level";
		String message = "Kindly update the Product Quantity";
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 15, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 20, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 25, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
		when(productService.getAllProducts()).thenReturn(products);
		
		emailScheduler.checkProductQuantity();
	}
}
