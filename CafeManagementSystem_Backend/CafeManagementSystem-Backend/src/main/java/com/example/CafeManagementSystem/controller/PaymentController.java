package com.example.CafeManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.entity.Payment;
import com.example.CafeManagementSystem.service.PaymentServiceImpl;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	
	public PaymentController(PaymentServiceImpl paymentServiceImpl) {
		super();
		this.paymentServiceImpl = paymentServiceImpl;
	}


	@PostMapping("/payment/{orderId}/{customerId}")
	public Payment insertPayment(@RequestBody Payment payment, @PathVariable int orderId, @PathVariable int customerId) {

		return paymentServiceImpl.insertPayment(payment, orderId, customerId);
	}

	@GetMapping("/payment")
	public List<Payment> getAllPayment() {

		return paymentServiceImpl.getAllPayment();
	}


	@GetMapping("/{paymentId}")
	public Payment getPaymentById(@PathVariable int paymentId) {

		return paymentServiceImpl.getPaymentById(paymentId);
	}


	@GetMapping("/payment/{paymentId}")
	public Payment updatePaymentById(@RequestBody Payment payment, @PathVariable int paymentId) {

		return paymentServiceImpl.updatePaymentById(payment, paymentId);
	}

	@DeleteMapping("/{paymentId}")
	public String deleteById(@PathVariable int paymentId) {

		paymentServiceImpl.deleteById(paymentId);
		return "Successfully deleted with id "+paymentId;
	}

	@GetMapping("/customer/{customerId}")
	public List<Payment> getPaymentByCustomerId(@PathVariable int customerId) {

		return paymentServiceImpl.getPaymentByCustomerId(customerId);
	}
}
