package com.einfochips.smartinventory.userrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.einfochips.smartinventory.model.Inventory;
import com.einfochips.smartinventory.model.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByProduct(Product product);

	@Query("SELECT i FROM Inventory i WHERE i.product.id = :productId")
	Inventory findByProductId(@Param("productId") Long productId);

	@Query("SELECT i FROM Inventory i WHERE i.product.name LIKE %:productName%")
	Page<Inventory> searchInventoryByProductName(@Param("productName") String productName, Pageable pageable);
}
