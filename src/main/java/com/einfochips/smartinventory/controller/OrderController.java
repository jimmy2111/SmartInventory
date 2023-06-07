package com.einfochips.smartinventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.einfochips.smartinventory.logging.LoggingAspect;
import com.einfochips.smartinventory.model.Customer;
import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Order;
import com.einfochips.smartinventory.model.OrderItem;
import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.service.CustomerService;
import com.einfochips.smartinventory.service.EmailService;
import com.einfochips.smartinventory.service.InventoryService;
import com.einfochips.smartinventory.service.OrderItemService;
import com.einfochips.smartinventory.service.OrderService;
import com.einfochips.smartinventory.service.ProductService;
import com.einfochips.smartinventory.userrepository.InventoryRepository;

@RestController
public class OrderController {
	
	private static final Logger logger=LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private EmailService emailService;
	@Value("${smartinventory.mail.to}")
	private String to;
	@Value("${smartinventory.mail.subject}")
	private String sub;
	@Value("${smartinventory.mail.message}")
	private String body;
	
	@PostMapping("/registerOrder")
	public ModelAndView placeOrder(@ModelAttribute Customer customer,
			@ModelAttribute Order order,
			@RequestParam(name = "id[]") Long[] id, 
			@RequestParam(name = "quantity[]") Integer[] quantity) {
		ModelAndView modelAndView = new ModelAndView("order");
		List<OrderItem> orderItems = new ArrayList<>();
		logger.info("Registering Order");
		for(int i=0;i<id.length;i++) {
			Long pid=id[i];
			Integer pquantity = quantity[i];
			Product product = productService.getProductById(pid);
			OrderItem orderItem = new OrderItem();
			
			orderItem.setProduct(product);
			orderItem.setQuantity(pquantity);
			
			orderItems.add(orderItem);
			
			int remquantity = product.getQuantity() - pquantity;
			product.setQuantity(remquantity);
			productService.updateProduct(pid, product);
			
			if(remquantity<=product.getThresholdQuantity()) {
				String subject = sub+" "+product.getId()+" "+product.getName();
				String message = body+" "+product.getId()+" "+product.getName();
				emailService.sendEmail(to, subject, message);	
			}
			Inventory inventory = inventoryService.findInventoryByProduct(product);
			System.out.println(inventory);
			if(remquantity<inventory.getQuantity()) {
				String subject = sub+" "+product.getId()+" "+product.getName();
				String message = body+" "+product.getId()+" "+product.getName();
				emailService.sendEmail(to, subject, message);
			}
			inventory.setQuantity(remquantity);
			inventoryService.updateInventory(inventory.getId(), inventory);
		}
		
		Customer c = customerService.getCustomerById(customer.getEmail());
		if(c==null) {
		customerService.createCustomer(customer);
		}
		else {
			customerService.updateCustomer(c.getEmail(), customer);
		}
		order.setCustomer(customer);
		order.setOrderItems(orderItems);
		Order o=orderService.createOrder(order);

		for(OrderItem orderItem : orderItems) {
		orderItem.setOrder(o);
		orderItemService.createOrderItem(orderItem);
		}
		modelAndView.addObject("success", "Ordered Placed Successfully");
		logger.info("Order Placed Successfully");
		return modelAndView;
	}
	
	

}
