package com.einfochips.smartinventory.userrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Supplier findByContact(String contact);
	Supplier findByNameAndAddressAndContact(String name,String Address,String contact);
	Page<Supplier> findByNameContainingIgnoreCase(String name,Pageable paging);
}
