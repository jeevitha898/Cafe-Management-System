package com.example.CafeManagementSystem.service;

import java.util.List;

import com.example.CafeManagementSystem.entity.Order;
import com.example.CafeManagementSystem.entity.OrderItem;

public interface OrderService {

	public Order insertRecord(Order order,int customerId);
	public List<Order> getAllRecord();
	public Order getRecordById(int orderId);
	public Order updateRecordById(int orderId, Order order);
	public void deleteRecordById(int orderId);
	public List<Order> getOrderByCustomerId(int customerId);
//	public void getPendingOrderByCustomerId(int customerId);
	public OrderItem addItem(OrderItem orderItem, int itemId, int customerId);
}


