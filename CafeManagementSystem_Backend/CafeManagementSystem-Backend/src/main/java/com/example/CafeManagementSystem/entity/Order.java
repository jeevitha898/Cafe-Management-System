package com.example.CafeManagementSystem.entity;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
@SequenceGenerator(name = "generator4", sequenceName = "order_gen4", initialValue = 400)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator4")
	private int orderId;
	private double totalPrice;
	private String orderStatus;
	private String paymentStatus;
	private LocalDate orderDate;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
//	@ManyToMany(cascade = CascadeType.MERGE)
//	private List<Menu> menu;

	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate localDate) {
		this.orderDate = localDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderStatus=" + orderStatus
				+ ", paymentStatus=" + paymentStatus + ", orderDate=" + orderDate + ", customer=" + customer + "]";
	}	
}
