package com.einfochips.smartinventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.Customer;
import com.einfochips.smartinventory.userrepository.CustomerRepository;

@Service
public class CustomerService {
	private static final Logger logger=LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository customerRepo;
	
	public List<Customer> getAllCustomers() {
		logger.info("Getting All Customers");
        return customerRepo.findAll();
    }

    public Customer getCustomerById(String string) {
    	logger.info("Finding Customer By Id");
        return customerRepo.findById(string).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
    	logger.info("Creating new Customer");
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(String customerId, Customer customerDetails) {
        Customer customer = getCustomerById(customerId);
        logger.info("Updating Existing Customer");
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        customer.setEmail(customerDetails.getEmail());
        customer.setOrders(customerDetails.getOrders());
        return customerRepo.save(customer);
    }

    public void deleteCustomer(String customerId) {
        Customer customer = getCustomerById(customerId);
        logger.info("Deleting Customer...");
        customerRepo.delete(customer);
    }

}
