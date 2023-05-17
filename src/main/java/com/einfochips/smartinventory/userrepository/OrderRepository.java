package com.einfochips.smartinventory.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
