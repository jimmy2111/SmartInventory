package com.einfochips.smartinventory.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String description;
    private int quantity;
    private int thresholdQuantity;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @JsonBackReference
    private Supplier supplier;
    
	public Product(Long id, String name, String description, int quantity, int thresholdQuantity, BigDecimal unitPrice,
			Supplier supplier) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.thresholdQuantity = thresholdQuantity;
		this.unitPrice = unitPrice;
		this.supplier = supplier;
	}
	
	public Product(Long id, String name, String description, int quantity, int thresholdQuantity,
			BigDecimal unitPrice) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.thresholdQuantity = thresholdQuantity;
		this.unitPrice = unitPrice;
	}

	public int getThresholdQuantity() {
		return thresholdQuantity;
	}

	public void setThresholdQuantity(int thresholdQuantity) {
		this.thresholdQuantity = thresholdQuantity;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", supplier.name=" + supplier.getName() + "]";
	}
	
    
    

}
