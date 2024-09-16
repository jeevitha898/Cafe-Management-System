package com.example.CafeManagementSystem.service;

import java.time.LocalDate;
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
import com.example.CafeManagementSystem.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemServiceImpl orderItemServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	private String order_status = "Pending";
	private String payment_status = "Pending";
	
	public OrderServiceImpl(OrderRepository orderRepository, OrderItemServiceImpl orderItemServiceImpl,
			CustomerServiceImpl customerServiceImpl) {
		super();
		this.orderRepository = orderRepository;
		this.orderItemServiceImpl = orderItemServiceImpl;
		this.customerServiceImpl = customerServiceImpl;
	}

	@Override
	public Order insertRecord(Order order, int customerId) {

		Customer customer =  customerServiceImpl.getCustomerById(customerId);
		
		double total_price = orderItemServiceImpl.getTotalPriceByCustomerId(customerId);
		System.out.println("////////////////////////////////////"+total_price+"////////////////////////////////////////////////////");
		
		order.setTotalPrice(total_price);
		order.setOrderStatus(order_status);
		order.setPaymentStatus(payment_status);
		order.setOrderDate(LocalDate.now());
		order.setCustomer(customer);
		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllRecord() {

		//getPendingOrderByCustomerId(100);
		return orderRepository.findAll();
	}

	@Override
	public Order getRecordById(int orderId) throws CustomerNotFoundException {

		Optional<Order> result = orderRepository.findById(orderId);
		Order existRecord;
		
		if(result.isPresent())
		{
			existRecord = result.get();
		}
		else {
			throw new CustomerNotFoundException("Order id is not found with "+orderId);
		}
		return existRecord;
	}

	@Override
	public Order updateRecordById(int orderId, Order order) {

		Order existRecord = this.getRecordById(orderId);
		
		existRecord.setOrderStatus(order.getOrderStatus());
		existRecord.setPaymentStatus(order.getPaymentStatus());
		existRecord.setTotalPrice(order.getTotalPrice());
		existRecord.setOrderDate(order.getOrderDate());
		
		orderRepository.save(existRecord);
		return existRecord;
	}

	@Override 
	public void deleteRecordById(int orderId) throws CustomerNotFoundException {

		if(orderRepository.existsById(orderId))
		{
			orderRepository.deleteById(orderId);
		}
		else {
			throw new CustomerNotFoundException("Order id is not found with "+orderId);
		}
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) {

		return orderRepository.findByCustomerId(customerId);
	}
	
	
	@Override
	public OrderItem addItem(OrderItem orderItem, int itemId, int customerId) throws CustomerNotFoundException {

		Order pendingOrder = orderRepository.findByCustomerIdAndPendingOrder(customerId);
		
		
		
			Menu menu = menuServiceImpl.getMenuById(itemId);
			Customer customer = customerServiceImpl.getCustomerById(customerId);
			List<OrderItem> oi = orderItemServiceImpl.getAllRecordOrderItem();
			
			int flag = 0;
			OrderItem existItems = null;
			
			if(oi.size() > 0) 
			{
				for(int i=0; i<oi.size();i++)
				{
					OrderItem o = orderItemServiceImpl.getRecordById(oi.get(i).getOrderItemId());
					
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
				orderItemServiceImpl.updateRecordById(existItems.getOrderItemId(), existItems);
		
				if(pendingOrder != null)
				{
					double totalPrice = orderItemServiceImpl.getTotalPriceByCustomerId(customerId);
					pendingOrder.setTotalPrice(totalPrice);
					this.updateRecordById(pendingOrder.getOrderId(), pendingOrder);
				}
				else { 
					throw new CustomerNotFoundException("not found pending order");
				}
				
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
				
				if(pendingOrder != null)
				{
					double totalPrice = orderItemServiceImpl.getTotalPriceByCustomerId(customerId);
					pendingOrder.setTotalPrice(totalPrice);
					this.updateRecordById(pendingOrder.getOrderId(), pendingOrder);
				}
				else { 
					throw new CustomerNotFoundException("not found pending order");
				}
				
				return orderItem;
			}
	}
}
	
	
	
	
	
	
	
	
	
//	@Override
//	public void getPendingOrderByCustomerId(int customerId) throws CustomerNotFoundException
//	{
//		
//		Order pendingOrder = orderRepository.findByCustomerIdAndPendingOrder(customerId);
//		System.out.println("/////////////////////////////////////////////"+pendingOrder+"////////////////////////////////////////////////////");
//		
//		if(pendingOrder != null)
//		{
//			double totalPrice = orderItemServiceImpl.getTotalPriceByCustomerId(customerId);
//			pendingOrder.setTotalPrice(totalPrice);
//			this.updateRecordById(pendingOrder.getOrderId(), pendingOrder);
//			System.out.println("/////////////////////////////////////////////"+pendingOrder+"////////////////////////////////////////////////////");
//		}
//		else { 
//			throw new CustomerNotFoundException("not found pending order");
//		}
////		return pendingOrder;
//	}


