package com.example.CafeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.CafeManagementSystem.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	@Query("select p from Payment p where p.customer.customerId = :customerId")
	public List<Payment> findByCustomerId(int customerId);
}
