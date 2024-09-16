package com.example.CafeManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.entity.OrderItem;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.OrderItemRepository;
import com.example.CafeManagementSystem.service.OrderItemServiceImpl;

@RestController
@RequestMapping("/api/OrderItems")
public class OrderItemController {

	@Autowired
	private OrderItemServiceImpl orderItemServiceImpl;

	public OrderItemController(OrderItemServiceImpl orderItemServiceImpl) {
		super();
		this.orderItemServiceImpl = orderItemServiceImpl;
	}
	
	@PostMapping("/{itemId}/{customerId}")
	public OrderItem insertOrderItem(@RequestBody OrderItem orderItem,@PathVariable int itemId,@PathVariable int customerId) {
		return orderItemServiceImpl.insertOrderItem(orderItem, itemId, customerId);
	}
	
	@GetMapping("/OrderItem")
	public List<OrderItem> getAllRecordOrderItem() {

		return orderItemServiceImpl.getAllRecordOrderItem();
	}
	
	
	@GetMapping("/OrderItem/{orderItemId}")
	public OrderItem getRecordById(@PathVariable int orderItemId) 
	{
		return orderItemServiceImpl.getRecordById(orderItemId);
	}
	
	@PutMapping("/OrderItem/update/{orderItemId}")
	public OrderItem updateRecordById(@PathVariable int orderItemId, @RequestBody OrderItem orderItem)
	{
		return orderItemServiceImpl.updateRecordById(orderItemId, orderItem);
	}
	
	
	@DeleteMapping("/deleting/{orderItemId}")
	public String deleteRecordById(@PathVariable int orderItemId)
	{
		orderItemServiceImpl.deleteRecordById(orderItemId);
		return "Deleted Successfully with id "+orderItemId;
	}
	
	@GetMapping("/customer/{customerId}")
	public List<OrderItem> getItemByCustomerId(@PathVariable int customerId) {

		return orderItemServiceImpl.getOrderItemByCustomerId(customerId);
	}
	
	@GetMapping("/{customerId}")
	public double getTotalPriceByCustomerId(@PathVariable int customerId) {
		return orderItemServiceImpl.getTotalPriceByCustomerId(customerId);
	}
}

