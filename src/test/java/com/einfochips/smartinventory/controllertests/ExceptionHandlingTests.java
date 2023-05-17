//package com.einfochips.smartinventory.controllertests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.einfochips.smartinventory.controller.SupplierController;
//import com.einfochips.smartinventory.exceptionhandling.ExceptionHandling;
//import com.einfochips.smartinventory.service.SupplierService;
//
//@WebMvcTest(ExceptionHandling.class)
//@WithMockUser(username="admin",roles={"USER","ADMIN"})
//public class ExceptionHandlingTests {
//	@MockBean
//	private SupplierService supplierService;
//	@Autowired
//	private MockMvc mockMvc;
//	@InjectMocks
//	private SupplierController supplierController;
//	@Test
//	public void testHandleException() throws Exception {
//	    // Mock the service method to throw an exception
//	    when(supplierService.searchSupplier(anyString(), anyInt(), anyInt(), anyString(), anyString()))
//	        .thenThrow(new Exception("Supplier Not Found"));
//
//	    // Call the controller method that will throw the exception
//	    ModelAndView result = supplierController.searchSupplier("I m Trader", 0, 4, "name", "asc");
//
//	    // Verify that the returned ModelAndView object is the "error" view
//	    assertEquals("error", result.getViewName());
//	    
//	    // Verify that the "errorMessage" attribute is set to the expected value
//	    assertEquals("Supplier Not Found", result.getModel().get("errorMessage"));
//	}
//}
