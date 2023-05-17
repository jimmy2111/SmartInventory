package com.einfochips.smartinventory.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private Long id;
	    private String name;
	    private String address;
	    @Id
	    private String email;
	    @OneToMany(mappedBy = "customer")
	    private List<Order> orders;

		public Customer(Long id, String name, String address, String email, List<Order> orders) {
			super();
			this.id = id;
			this.name = name;
			this.address = address;
			this.email = email;
			this.orders = orders;
		}

		public Customer() {
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
	    
	    
	    

}
