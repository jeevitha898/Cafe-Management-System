package com.example.CafeManagementSystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "generator6", sequenceName = "myOrders_gen6", initialValue = 800)
public class MyOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator6")
	private int myOrdersId;
	
	private int Quantity;
	private double totalPrice;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_id")
	private Menu menu;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public MyOrders() {
		super();
	}

	public int getMyOrdersId() {
		return myOrdersId;
	}

	public void setMyOrdersId(int myOrdersId) {
		this.myOrdersId = myOrdersId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "MyOrders [myOrdersId=" + myOrdersId + ", Quantity=" + Quantity + ", totalPrice=" + totalPrice
				+ ", menu=" + menu + ", order=" + order + ", customer=" + customer + "]";
	}

}
