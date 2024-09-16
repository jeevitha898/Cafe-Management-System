package com.example.CafeManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.entity.MyOrders;
import com.example.CafeManagementSystem.service.MyOrdersServiceImple;

@RestController
@RequestMapping("/api/myOrders")
public class MyOrdersContoller {
	
	@Autowired
	private MyOrdersServiceImple myOrdersServiceImple;

	public MyOrdersContoller(MyOrdersServiceImple myOrdersServiceImple) {
		super();
		this.myOrdersServiceImple = myOrdersServiceImple;
	}

	@PostMapping("/myOrder/{itemId}/{orderId}/{customerId}")
	public MyOrders insertMyOrders(@RequestBody MyOrders myOrders, @PathVariable int itemId, @PathVariable int orderId, @PathVariable int customerId) {

		return myOrdersServiceImple.insertMyOrders(myOrders,itemId, orderId, customerId);
	}

//	@GetMapping("/myOrder")
//	public List<MyOrders> getAllMyOrders() {
//
//		return myOrdersServiceImple.getAllMyOrders();
//	}
//
//	@GetMapping("/myOrder/{myOrderId}")
//	public MyOrders getMyOrdersById(@PathVariable int myOrdersId) {
//
//		return myOrdersServiceImple.getMyOrdersById(myOrdersId);
//	}
//
//	@PutMapping("/myOrder/{myOrderid}")
//	public MyOrders updateMyOrdersById(@RequestBody MyOrders myOrders, @PathVariable int myOrdersId) {
//
//		return myOrdersServiceImple.updateMyOrdersById(myOrders, myOrdersId);
//	}
//
//	@DeleteMapping("/{myOrderId}")
//	public String deleteById(int myOrdersId) {
//
//		myOrdersServiceImple.deleteById(myOrdersId);
//		return "Successfully deleted with id "+myOrdersId;
//	}
//
//	@GetMapping("/customer/{customerId}")
//	public List<MyOrders> getMyOrdersByCustomerId(int customerId) {
//
//		return myOrdersServiceImple.getMyOrdersByCustomerId(customerId);
//	}
	
}
