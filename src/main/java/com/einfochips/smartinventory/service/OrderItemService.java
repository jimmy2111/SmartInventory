package com.einfochips.smartinventory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.OrderItem;
import com.einfochips.smartinventory.userrepository.OrderItemRepository;

@Service
public class OrderItemService {
	private static final Logger logger=LoggerFactory.getLogger(OrderItemService.class);
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	public List<OrderItem> getAllOrderItems() {
		logger.info("Getting All Order Items");
        return orderItemRepo.findAll();
    }

    public OrderItem getOrderItemById(Long orderItemId) {
    	logger.info("Getting OrderItem By Id");
        return orderItemRepo.findById(orderItemId).orElse(null);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
    	logger.info("Creating new OrderItem");
        return orderItemRepo.save(orderItem);
    }

    public OrderItem updateOrderItem(Long orderItemId, OrderItem orderItemDetails) {
        OrderItem orderItem = getOrderItemById(orderItemId);
        logger.info("Updating OrderItem");
        orderItem.setProduct(orderItemDetails.getProduct());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setOrder(orderItemDetails.getOrder());
        return orderItemRepo.save(orderItemDetails);
    }
    
    public void deleteOrderItem(Long orderItemId) {
    	OrderItem orderItem=getOrderItemById(orderItemId);
    	logger.info("Deleting Order Item...");
    	orderItemRepo.delete(orderItem);
    }

}
