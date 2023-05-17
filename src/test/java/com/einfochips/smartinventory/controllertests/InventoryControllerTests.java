package com.einfochips.smartinventory.controllertests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.einfochips.smartinventory.controller.InventoryController;
import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.InventoryService;

@WebMvcTest(InventoryController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class InventoryControllerTests {
	@MockBean
	private InventoryService inventoryService;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void viewInventoryTest() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
		Product product = new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products));
		List<Inventory> inventory = new ArrayList<>();
		inventory.add(new Inventory(23L, product, product.getQuantity(), product.getThresholdQuantity()));
		Page<Inventory> page = new PageImpl<>(inventory);
		
		when(inventoryService.getAllInventoryByPagingAndSorting(0, 5, "id", "asc")).thenReturn(page);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/viewallinventory");
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("inventory"))
				.andReturn();
	}
	@Test
	public void searchInventoryTest() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
		Product product = new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products));
		List<Inventory> inventory = new ArrayList<>();
		inventory.add(new Inventory(23L, product, product.getQuantity(), product.getThresholdQuantity()));
		Page<Inventory> page = new PageImpl<>(inventory);
		when(inventoryService.searchInventory(product.getName(), 0, 5, "id", "asc")).thenReturn(page);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/searchinventory").param("name", "Fanta");
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful()).andReturn();
	}
	@Test
	public void searchInventory_ExceptionTest() throws Exception {
		when(inventoryService.searchInventory("NewInventory", 0, 5, "id", "asc")).thenReturn(new PageImpl<>(Collections.emptyList()));
		RequestBuilder request = MockMvcRequestBuilders.get("/searchinventory").param("name", "NewInventory");
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(model().attribute("errorMessage", "Inventory Not Found"))
				.andReturn();
	}
}
