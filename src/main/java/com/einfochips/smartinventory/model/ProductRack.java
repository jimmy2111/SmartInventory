package com.einfochips.smartinventory.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class ProductRack {
	@Id
	private Long rackId;
	private Long productId;
	private String name;
	private int quantity;
	
	public ProductRack(Long rackId, Long productId, String name, int quantity) {
		super();
		this.rackId = rackId;
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
	}
	public ProductRack() {
		super();
	}
	public Long getRackId() {
		return rackId;
	}
	public void setRackId(Long rackId) {
		this.rackId = rackId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductRack [rackId=" + rackId + ", productId=" + productId + ", name=" + name + ", quantity="
				+ quantity + "]";
	}
	
	
}
