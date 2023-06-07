package com.einfochips.smartinventory.controllertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.controller.OrderController;
import com.einfochips.smartinventory.model.Customer;
import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Order;
import com.einfochips.smartinventory.model.OrderItem;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.CustomerService;
import com.einfochips.smartinventory.service.EmailService;
import com.einfochips.smartinventory.service.InventoryService;
import com.einfochips.smartinventory.service.OrderItemService;
import com.einfochips.smartinventory.service.OrderService;
import com.einfochips.smartinventory.service.ProductService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class OrderControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private OrderController orderController;
	@MockBean
	private OrderService orderService;
	@MockBean
	private ProductService productService;
	@MockBean
	private CustomerService customerService;
	@MockBean
	private OrderItemService orderItemService;
	@MockBean
	private InventoryService inventoryService;
	@MockBean
	private EmailService emailService;
	@Test
	public void placeOrderTest() {
		List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Fanta", "Cold Drink", 150, 20, new BigDecimal(65.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(2L, "Coke", "Cold Drink", 200, 30, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        products.add(new Product(3L, "Pepsi", "Cold Drink", 250, 40, new BigDecimal(75.00),new Supplier(98L, "D C Traders", "Ahmedabad", "963800588", products)));
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier(98L, "D C Traders", "Ahmedabad", "9898698675", products));
        suppliers.add(new Supplier(99L, "J K Trading", "Godhra", "989867569", products));
        List<Inventory> inventory = new ArrayList<>();
        inventory.add(new Inventory(1L, products.get(0), products.get(0).getQuantity(), products.get(0).getThresholdQuantity()));
        inventory.add(new Inventory(2L, products.get(1), products.get(1).getQuantity(), products.get(1).getThresholdQuantity()));
        List<Order> orders = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(20L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
        orderItems.add(new OrderItem(1L, 2, new Order(2L, new Date(2023, 05, 20), customers.get(0), orderItems), products.get(0)));
        orders.add(new Order(2L, new Date(2023, 05, 20), customers.get(0), orderItems));
        Long id[]= {2L,3L};
        Integer quantity[] = {2,3};
        when(productService.getProductById(any())).thenReturn(products.get(0));
        when(productService.updateProduct(any(),any())).thenReturn(products.get(0));
        when(inventoryService.findInventoryByProduct(products.get(0))).thenReturn(inventory.get(0));
        ModelAndView mav = orderController.placeOrder(customers.get(0), orders.get(0), id, quantity);
        assertEquals("order", mav.getViewName());
	}
	
}
