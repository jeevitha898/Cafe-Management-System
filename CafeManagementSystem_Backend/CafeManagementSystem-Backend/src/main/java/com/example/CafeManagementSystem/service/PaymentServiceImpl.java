package com.example.CafeManagementSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.entity.Menu;
import com.example.CafeManagementSystem.entity.MyOrders;
import com.example.CafeManagementSystem.entity.Order;
import com.example.CafeManagementSystem.entity.OrderItem;
import com.example.CafeManagementSystem.entity.Payment;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.MyOrdersRepository;
import com.example.CafeManagementSystem.repository.OrderItemRepository;
import com.example.CafeManagementSystem.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private OrderItemServiceImpl orderItemServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private MyOrdersServiceImple myOrdersServiceImple;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public PaymentServiceImpl(PaymentRepository paymentRepository, OrderServiceImpl orderServiceImpl,
			OrderItemServiceImpl orderItemServiceImpl, CustomerServiceImpl customerServiceImpl,
			MyOrdersServiceImple myOrdersServiceImple, OrderItemRepository orderItemRepository) {
		super();
		this.paymentRepository = paymentRepository;
		this.orderServiceImpl = orderServiceImpl;
		this.orderItemServiceImpl = orderItemServiceImpl;
		this.customerServiceImpl = customerServiceImpl;
		this.myOrdersServiceImple = myOrdersServiceImple;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public Payment insertPayment(Payment payment, int orderId, int customerId) {

		Order order = orderServiceImpl.getRecordById(orderId);
		Customer customer = customerServiceImpl.getCustomerById(customerId);
		
		payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		payment.setCustomer(customer);
		
		if(payment.getTotalPrice() == payment.getPaidAmount())
		{
			order.setPaymentStatus("PAID");
			order.setOrderStatus("Deliverd");
		}
		else {
			order.setPaymentStatus("NOT-PAID");
			order.setOrderStatus("Payment-Pending");
		}
	    paymentRepository.save(payment);
	    
	    List<OrderItem> existOrderItem = orderItemServiceImpl.getOrderItemByCustomerId(customerId);
	    
	    if(existOrderItem.size()>0)
	    {
	    	
	    	for(int i=0; i<existOrderItem.size(); i++)
		    {
	    	    MyOrders myOrders = new MyOrders() ;
				
				int item_id = existOrderItem.get(i).getMenu().getItemId();
				int customer_id = existOrderItem.get(i).getCustomer().getCustomerId();
				myOrders.setQuantity(existOrderItem.get(i).getQuantity());
				myOrders.setTotalPrice(existOrderItem.get(i).getTotalPrice());
				myOrdersServiceImple.insertMyOrders(myOrders, item_id, orderId, customer_id);
		    	
		    	int orderItem_id = existOrderItem.get(i).getOrderItemId();
		    	orderItemRepository.deleteById(existOrderItem.get(i).getOrderItemId());
		    }
	    }
	    
	    return payment;
	}

	@Override
	public List<Payment> getAllPayment() {

		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(int paymentId) throws CustomerNotFoundException {

		Optional<Payment> result = paymentRepository.findById(paymentId);
		Payment payment;
		
		if(result.isPresent())
		{
			payment = result.get();
		}
		else
		{
			throw new CustomerNotFoundException("Pyment id : "+paymentId+" is found");
		}
		return payment;
	}

	@Override
	public Payment updatePaymentById(Payment payment, int paymentId) {
		
		Payment existPayment = this.getPaymentById(paymentId);

		existPayment.setTotalPrice(payment.getTotalPrice());
		existPayment.setPaidDate(payment.getPaidDate());
		existPayment.setPaidAmount(payment.getPaidAmount());
		existPayment.setOrderId(payment.getOrderId());
		existPayment.setCustomer(payment.getCustomer());
		paymentRepository.save(existPayment);
		return existPayment;
	}

	@Override
	public void deleteById(int paymentId) throws CustomerNotFoundException {

		if(paymentRepository.existsById(paymentId))
		{
			paymentRepository.deleteById(paymentId);
		}
		else {
			throw new CustomerNotFoundException("Payment id: "+paymentId+" is not fount ");
		}
		
	}

	@Override
	public List<Payment> getPaymentByCustomerId(int customerId) {

		return paymentRepository.findByCustomerId(customerId);
	}

}
