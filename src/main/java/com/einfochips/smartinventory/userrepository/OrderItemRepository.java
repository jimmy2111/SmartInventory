package com.einfochips.smartinventory.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
