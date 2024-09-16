package com.example.CafeManagementSystem.service;

import java.util.List;

import com.example.CafeManagementSystem.entity.MyOrders;

public interface MyOrdersService {
	
	public MyOrders insertMyOrders(MyOrders myOrders,int itemId, int orderId, int customerId);
	public List<MyOrders> getAllMyOrders();
	public MyOrders getMyOrdersById(int myOrdersId);
	public MyOrders updateMyOrdersById(MyOrders myOrders, int myOrdersId);
	public void deleteById(int myOrdersId);
	public List<MyOrders> getMyOrdersByCustomerId(int customerId);

}
