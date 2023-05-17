package com.einfochips.smartinventory.userrepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einfochips.smartinventory.model.Product;
import com.einfochips.smartinventory.model.Supplier;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByNameAndSupplier(String name,Supplier supplier);
	Page<Product> findByNameContainingIgnoreCase(String name,Pageable paging);
}
