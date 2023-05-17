package com.einfochips.smartinventory.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
