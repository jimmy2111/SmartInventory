package com.einfochips.smartinventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.Order;
import com.einfochips.smartinventory.userrepository.OrderRepository;
@Service
public class OrderService {
	private static final Logger logger=LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> getAllOrders() {
		logger.info("Finding All Orders");
        return orderRepo.findAll();
    }

    public Order getOrderById(Long orderId) {
    	logger.info("Finding Order through Id");
        return orderRepo.findById(orderId).orElse(null);
    }

    public Order createOrder(Order order) {
    	logger.info("Creating New Order");
        return orderRepo.save(order);
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = getOrderById(orderId);
        logger.info("Updating Existing Order");
        order.setDate(orderDetails.getDate());
        order.setCustomer(orderDetails.getCustomer());
        order.setOrderItems(orderDetails.getOrderItems());
        return orderRepo.save(order);
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        logger.info("Deleting Order...");
        orderRepo.delete(order);
    }

}
