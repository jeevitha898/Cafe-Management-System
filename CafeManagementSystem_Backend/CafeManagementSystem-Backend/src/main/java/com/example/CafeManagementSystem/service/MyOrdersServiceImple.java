package com.example.CafeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.entity.Menu;
import com.example.CafeManagementSystem.entity.MyOrders;
import com.example.CafeManagementSystem.entity.Order;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.MyOrdersRepository;

@Service
public class MyOrdersServiceImple implements MyOrdersService {

	@Autowired
	private MyOrdersRepository myOrdersRepository;
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	public MyOrdersServiceImple(MyOrdersRepository myOrdersRepository, MenuServiceImpl menuServiceImpl,
			OrderServiceImpl orderServiceImpl, CustomerServiceImpl customerServiceImpl) {
		super();
		this.myOrdersRepository = myOrdersRepository;
		this.menuServiceImpl = menuServiceImpl;
		this.orderServiceImpl = orderServiceImpl;
		this.customerServiceImpl = customerServiceImpl;
	}

	@Override
	public MyOrders insertMyOrders(MyOrders myOrders, int itemId, int orderId, int customerId) {

		Menu menu = menuServiceImpl.getMenuById(itemId);
		Order order = orderServiceImpl.getRecordById(orderId);
		Customer customer = customerServiceImpl.getCustomerById(customerId);
		
		myOrders.setQuantity(myOrders.getQuantity());
		myOrders.setTotalPrice(myOrders.getTotalPrice());
		myOrders.setMenu(menu);
		myOrders.setOrder(order);
		myOrders.setCustomer(customer);
		
		return myOrdersRepository.save(myOrders);
	}

	@Override
	public List<MyOrders> getAllMyOrders() {

		return myOrdersRepository.findAll();
	}

	@Override
	public MyOrders getMyOrdersById(int myOrdersId) throws CustomerNotFoundException {

		Optional<MyOrders> result = myOrdersRepository.findById(myOrdersId);
		MyOrders myOrders ;
		
		if(result.isPresent())
		{
			myOrders = result.get();
		}
		else {
			throw new CustomerNotFoundException("MyOrders id: "+myOrdersId+" not found");
		}
		return myOrders;
	}

	@Override
	public MyOrders updateMyOrdersById(MyOrders myOrders, int myOrdersId) {

		MyOrders existMyOrders = this.getMyOrdersById(myOrdersId);
		
		existMyOrders.setQuantity(myOrders.getQuantity());
		existMyOrders.setTotalPrice(myOrders.getTotalPrice());
		existMyOrders.setMenu(myOrders.getMenu());
		existMyOrders.setOrder(myOrders.getOrder());
		existMyOrders.setCustomer(myOrders.getCustomer());
		myOrdersRepository.save(existMyOrders);
		
		return existMyOrders;
	}

	@Override
	public void deleteById(int myOrdersId) throws CustomerNotFoundException {
		
		if(myOrdersRepository.existsById(myOrdersId))
		{
			myOrdersRepository.deleteById(myOrdersId);
		}
		else {
			throw new CustomerNotFoundException("MyOrders id: "+myOrdersId+" not found");
		}
		
	}

	@Override
	public List<MyOrders> getMyOrdersByCustomerId(int customerId) {

		return myOrdersRepository.findByCustomerId(customerId);
	}

}
