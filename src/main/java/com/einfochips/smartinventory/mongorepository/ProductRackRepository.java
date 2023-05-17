package com.einfochips.smartinventory.mongorepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.einfochips.smartinventory.model.ProductRack;

public interface ProductRackRepository extends MongoRepository<ProductRack, String> {
	List<ProductRack> findAllByRackId(Long rackId);
}
