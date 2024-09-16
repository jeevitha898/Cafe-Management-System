package com.example.CafeManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.entity.Menu;
import com.example.CafeManagementSystem.entity.Order;
import com.example.CafeManagementSystem.entity.OrderItem;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	private OrderServiceImpl orderServiceImpl;

	public OrderItemServiceImpl(OrderItemRepository orderItemRepository, MenuServiceImpl menuServiceImpl,
			CustomerServiceImpl customerServiceImpl) {
		super();
		this.orderItemRepository = orderItemRepository;
		this.menuServiceImpl = menuServiceImpl;
		this.customerServiceImpl = customerServiceImpl;
	}

	@Override
	public OrderItem insertOrderItem(OrderItem orderItem, int itemId, int customerId) {

		Menu menu = menuServiceImpl.getMenuById(itemId);
		Customer customer = customerServiceImpl.getCustomerById(customerId);
		List<OrderItem> oi = this.getAllRecordOrderItem();
		
		int flag = 0;
		OrderItem existItems = null;
		
		if(oi.size() > 0) 
		{
			for(int i=0; i<oi.size();i++)
			{
				OrderItem o = this.getRecordById(oi.get(i).getOrderItemId());
				
				if(o.getMenu().getItemId() == itemId && o.getCustomer().getCustomerId() == customerId)
				{
					flag = 1;
					existItems = o;
				}
			}
		}
	
		if(flag == 1 && existItems != null)
		{
			existItems.setQuantity(existItems.getQuantity() + orderItem.getQuantity());
			existItems.setPrice(menu.getPrice());
			existItems.setTotalPrice(menu.getPrice() * existItems.getQuantity());
			existItems.setCustomer(customer);
			System.out.println("11111111111111111111111111111111111111111");
			this.updateRecordById(existItems.getOrderItemId(), existItems);

			return existItems;
		}
		else {
			orderItem.setQuantity(orderItem.getQuantity());
			orderItem.setPrice(menu.getPrice());
			orderItem.setTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
			orderItem.setMenu(menu);
			orderItem.setCustomer(customer);
			System.out.println("222222222222222222222222222222222222222222");
			orderItemRepository.save(orderItem);
			
			return orderItem;
		}
	}

	
	@Override
	public List<OrderItem> getAllRecordOrderItem() {

		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem getRecordById(int orderItemId) throws CustomerNotFoundException{
		
	Optional<OrderItem> result = orderItemRepository.findById(orderItemId);
	OrderItem existItem;
	
	if(result.isPresent())
	{
		existItem = result.get();
	}
	else
	{
		throw new CustomerNotFoundException("Item id not found "+orderItemId);
	}
	
	return existItem;
	
	}

	@Override
	public OrderItem updateRecordById(int orderItemId, OrderItem orderItem) throws CustomerNotFoundException {

		Optional<OrderItem> existItem = orderItemRepository.findById(orderItemId);
		OrderItem newItem;
		
		if(existItem.isPresent())
		{
			newItem = existItem.get();
		}
		else {
			throw new CustomerNotFoundException("Order Item id is not found "+orderItemId);
		}
		
		newItem.setQuantity(orderItem.getQuantity());
		newItem.setPrice(orderItem.getPrice());
		newItem.setTotalPrice(orderItem.getTotalPrice());
		newItem.setMenu(orderItem.getMenu());
		newItem.setCustomer(orderItem.getCustomer());
		orderItemRepository.save(newItem);
		return newItem;
	}

	@Override
	public void deleteRecordById(int orderItemId) throws CustomerNotFoundException
	{
		Optional<OrderItem> result = orderItemRepository.findById(orderItemId);
		OrderItem existItem;
		
		if(result.isPresent())
		{
			existItem = result.get();
			
			if(existItem.getQuantity() > 1)
			{
				existItem.setQuantity(existItem.getQuantity() - 1);
				existItem.setTotalPrice(existItem.getTotalPrice() - existItem.getPrice());
				this.updateRecordById(orderItemId, existItem);
			}
			else if(existItem.getQuantity() == 1)
			{
				orderItemRepository.deleteById(existItem.getOrderItemId());
			}
		}
//		if(orderItemRepository.existsById(orderItemId))
//		{
//			orderItemRepository.deleteById(orderItemId);
//		}
//		else
//		{
//			throw new CustomerNotFoundException("Order Item id not found "+orderItemId);
//		}
		
	}

	@Override
	public List<OrderItem> getOrderItemByCustomerId(int customerId){

		return orderItemRepository.findByOrderItemId(customerId);
	}

	@Override
	public double getTotalPriceByCustomerId(int customerId) {
		System.out.println("//////////////////////////////////////// "+customerId+" //////////////////////////////////////////////////////////////");
		double totalPrice = orderItemRepository.findByCustomerId(customerId);
		System.out.println("//////////////////////////////////////// "+totalPrice+" //////////////////////////////////////////////////////////////");
		return totalPrice;
	}
	
}

