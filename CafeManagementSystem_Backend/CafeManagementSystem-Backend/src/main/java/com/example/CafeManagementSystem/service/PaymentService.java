package com.example.CafeManagementSystem.service;

import java.util.List;

import com.example.CafeManagementSystem.entity.Payment;

public interface PaymentService {

	public Payment insertPayment(Payment payment, int orderId, int customerId);
	public List<Payment> getAllPayment();
	public Payment getPaymentById(int paymentId);
	public Payment updatePaymentById(Payment payment, int paymentId);
	public void deleteById(int paymentId);
	public List<Payment> getPaymentByCustomerId(int customerId);
	
}
