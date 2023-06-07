package com.einfochips.smartinventory.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.einfochips.smartinventory.model.Customer;
import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Order;
import com.einfochips.smartinventory.model.OrderItem;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;
import com.einfochips.smartinventory.service.OrderService;
import com.einfochips.smartinventory.userrepository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;
	@Mock
	private OrderRepository orderRepository;
	
	@Test
	public void getAllOrdersTest(){
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
        
        when(orderRepository.findAll()).thenReturn(orders);
        
        List<Order> o = orderService.getAllOrders();
        assertEquals(orders, o);
	}
	@Test
	public void getOrderByIdTest() {
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
        
        when(orderRepository.findById(any())).thenReturn(Optional.of(orders.get(0)));
        
        Order o = orderService.getOrderById(2L);
        assertEquals(orders.get(0), o);
	}
	@Test
	public void createOrderTest() {
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
        
        when(orderRepository.save(any())).thenReturn(orders.get(0));
        Order o = orderService.createOrder(orders.get(0));
        assertEquals(orders.get(0), o);
	}
	@Test
	public void updateOrderTest() {
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
        
        when(orderRepository.save(any())).thenReturn(orders.get(0));
        when(orderRepository.findById(any())).thenReturn(Optional.of(orders.get(0)));
        Order o = orderService.updateOrder(2L, orders.get(0));
        assertEquals(orders.get(0), o);
	}
	@Test
	public void deleteOrderTest() {
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
        
        when(orderRepository.findById(any())).thenReturn(Optional.of(orders.get(0)));
        boolean result = orderService.deleteOrder(2L);
        assertTrue(result);
	}
	
}
