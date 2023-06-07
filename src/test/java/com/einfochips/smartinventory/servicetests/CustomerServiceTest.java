package com.einfochips.smartinventory.servicetests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.einfochips.smartinventory.model.Customer;
import com.einfochips.smartinventory.model.Order;
import com.einfochips.smartinventory.service.CustomerService;
import com.einfochips.smartinventory.userrepository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void getAllCustomersTest() {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
		
		when(customerRepository.findAll()).thenReturn(customers);
		List<Customer> c = customerService.getAllCustomers();
		assertEquals(customers, c);
	}
	@Test
	public void getCustomerByIdTest() {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
		
		when(customerRepository.findById(any())).thenReturn(Optional.of(customers.get(0)));
		Customer c = customerService.getCustomerById(customers.get(0).getEmail());
		assertEquals(customers.get(0), c);
	}
	@Test
	public void createCustomerTest() {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
		
		when(customerRepository.save(any())).thenReturn(customers.get(0));
		Customer c = customerService.createCustomer(customers.get(0));
		assertEquals(customers.get(0), c);
	}
	@Test
	public void updateCustomerTest() {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
		
		when(customerRepository.findById(any())).thenReturn(Optional.of(customers.get(0)));
		when(customerRepository.save(any())).thenReturn(customers.get(0));
		Customer c = customerService.updateCustomer(customers.get(0).getEmail(), customers.get(0));
		assertEquals(customers.get(0), c);
	}
	@Test
	public void deleteCustomerTest() {
		List<Order> orders = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Jimmy", "Ahmedabad", "ja@ja.com", orders));
		
		when(customerRepository.findById(any())).thenReturn(Optional.of(customers.get(0)));
		boolean result = customerService.deleteCustomer(customers.get(0).getEmail());
		assertTrue(result);
	}
}
