package com.einfochips.smartinventory.controllertests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.controller.ProductController;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.service.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class ProductControllerTests {
	@InjectMocks
	private ProductController productController;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductService productService;
	@MockBean
	private SupplierService supplierService;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void productsPageTest() throws Exception {
		 List<Product> products = new ArrayList<>();
	        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
	        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
	        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
		Page<Product> page = new PageImpl<>(products);
     when(productService.getAllProductsByPagingAndSorting(0, 4, "name", "asc")).thenReturn(page);
		RequestBuilder request = MockMvcRequestBuilders.get("/viewproducts")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				//.andExpect(content().json("[{id:75,name:Fanta,description:Cold Drink,quantity:150,thresholdQuantity:20,unitPrice:65.00}]"))
				.andReturn();
		//JSONAssert.assertEquals("[]", result.getResponse().getContentAsString(), false);
		//JSONAssert.assertEquals("[{\"id\":75,\"name\":\"Fanta\",\"description\":\"Cold Drink\",\"quantity\":150,\"thresholdQuantity\":20,\"unitPrice\":65}]",result.getResponse().getContentAsString(),false);
	}
	@Test
    public void testGetAddProductsPage() throws Exception {
//        List<Supplier> suppliers = Arrays.asList(new Supplier(), new Supplier());
//        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

			RequestBuilder request = MockMvcRequestBuilders.get("/addProducts");
		
			MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(model().attributeExists("suppliers"))
				.andExpect(view().name("addProducts"))
				.andReturn();
    }
	@Test
	public void viewAllProductsByPagingAndSortingTest() throws Exception {
		 List<Product> products = new ArrayList<>();
	        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
	        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
	        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
		Page<Product> page = new PageImpl<>(products);
        when(productService.getAllProductsByPagingAndSorting(0, 4, "name", "asc")).thenReturn(page);

		RequestBuilder request = MockMvcRequestBuilders.get("/viewallproducts");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("products"))
				.andExpect(model().attribute("revSortOrder", "desc"))
				.andReturn();
	}

	@Test
	public void testSearchProduct() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
	    Page<Product> page = new PageImpl<>(products);
	    when(productService.searchProducts(any(), any(), any(), any(), any())).thenReturn(page);
	    
	    ModelAndView mav = productController.searchProduct("Fanta", 0, 4, "name", "asc");
	    assertEquals("products", mav.getViewName());
	}
	@Test
	public void searchProduct_ExceptionTest() throws Exception {
		when(productService.searchProducts(any(), any(), any(), any(), any())).thenReturn(new PageImpl<>(Collections.emptyList()));
		//ModelAndView mav = productController.searchProduct("NewProduct", 0, 4, "name", "asc");
	    assertThrows(Exception.class, ()->{productController.searchProduct("NewProduct", 0, 4, "name", "asc");});
	}
	@Test
	public void testSaveProduct() throws Exception {
		List<Product> products = new ArrayList<>();
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
		Product product = new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products));
		when(productService.createProduct(any())).thenReturn(product);
		
		ModelAndView mav = productController.saveProduct(product, product.getId());
		assertEquals("addProducts", mav.getViewName());
	}
	
}
