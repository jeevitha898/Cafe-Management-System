package com.example.CafeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CafeManagementSystem.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query("select o from Order o where o.paymentStatus = 'Pending' and o.customer.customerId = :customerId")
	public List<Order> findByCustomerId(int customerId);
	
	@Query("select o from Order o where o.customer.customerId = :customerId")
	public Order findByCustomerIdAndPendingOrder(int customerId);
}
