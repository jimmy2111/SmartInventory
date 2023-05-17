package com.einfochips.smartinventory.controllertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.einfochips.smartinventory.controller.SupplierController;
import com.einfochips.smartinventory.exceptionhandling.ExceptionHandling;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.SupplierService;

@WebMvcTest(SupplierController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class SupplierControllerTests {

	@MockBean
	private SupplierService supplierService;
	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private SupplierController supplierController;
	@Test
	public void addSupplierPageTest() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/addSuppliers");
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("addSupplier"))
				.andReturn();
	}
	
	@Test
	public void viewSuppliersTest() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        
        Page<Supplier> page = new PageImpl<>(suppliers);
        when(supplierService.getSupplierByPagingAndSorting(0, 4, "id", "asc")).thenReturn(page);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/viewsuppliers");
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().is2xxSuccessful())
        		.andReturn();
        ModelAndView mav = result.getModelAndView();
        assertEquals("suppliers", mav.getViewName());
	}
	@Test
	public void searchSupplierTest() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        
        Page<Supplier> page = new PageImpl<>(suppliers);
        when(supplierService.searchSupplier("D C Traders", 0, 4, "name", "asc")).thenReturn(page);
        
        
        RequestBuilder request = MockMvcRequestBuilders.get("/searchsupplier").param("name", "D C Traders");
        MvcResult result = mockMvc.perform(request)
        		.andExpect(status().is2xxSuccessful())
        		.andExpect(view().name("suppliers")).andReturn();
	}
	@Test
	public void searchSupplier_ExceptionTest() throws Exception {
	    when(supplierService.searchSupplier("I m Trader", 0, 4, "name", "asc")).thenReturn(new PageImpl<>(Collections.emptyList()));

	    mockMvc.perform(MockMvcRequestBuilders.get("/searchsupplier")
	            .param("name", "I m Trader")
	            .param("pageNo", "0")
	            .param("pageSize", "4")
	            .param("sortField", "name")
	            .param("sortOrder", "asc"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "Supplier Not Found"))
	            .andExpect(MockMvcResultMatchers.view().name("error"));
	}
	
	
}
