package com.example.CafeManagementSystem.service;

import java.util.List;

import com.example.CafeManagementSystem.entity.OrderItem;

public interface OrderItemService {

	public OrderItem insertOrderItem(OrderItem orderItem, int itemId, int customerId);
	public List<OrderItem> getAllRecordOrderItem();
	public OrderItem getRecordById(int orderItemId);
	public OrderItem updateRecordById(int orderItemId, OrderItem orderItem);
	public void deleteRecordById(int orderItemId);
	public List<OrderItem> getOrderItemByCustomerId(int customerId);
	public double getTotalPriceByCustomerId(int customerId); 
}
