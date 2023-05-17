package com.einfochips.smartinventory.model;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private Date date;
	    @ManyToOne
	    @JoinColumn(name = "customer_email", referencedColumnName = "email")
	    private Customer customer;
	    @OneToMany(mappedBy = "order")
	    private List<OrderItem> orderItems;

		public Order() {
			super();
		}

		public Order(Long id, Date date, Customer customer, List<OrderItem> orderItems) {
			super();
			this.id = id;
			this.date = date;
			this.customer = customer;
			this.orderItems = orderItems;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public List<OrderItem> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", date=" + date + ", customer=" + customer + "]";
		}
	    
	    

}
