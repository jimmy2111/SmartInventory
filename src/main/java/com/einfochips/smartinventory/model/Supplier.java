package com.einfochips.smartinventory.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String address;
    private String contact;
    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference
    private List<Product> products;
    
    

	public Supplier() {
		super();
	}

	public Supplier(Long id, String name, String address, String contact, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.products = products;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + ", products="
				+ products.size() + "]";
	}
    
    

}
