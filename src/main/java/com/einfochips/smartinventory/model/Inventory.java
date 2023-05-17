package com.einfochips.smartinventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	private int quantity;
	private int threshold;
	 
	public Inventory(Long id, Product product, int quantity,int threshold) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.threshold=threshold;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Inventory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product=" + product + ", quantity=" + quantity + ", threshold=" + threshold
				+ "]";
	}
	 
	 

}
