package com.example.CafeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CafeManagementSystem.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	@Query("select o from OrderItem o where o.customer.customerId = :customerId")
	List<OrderItem> findByOrderItemId(int customerId);
	
	@Query("select sum(o.totalPrice) from OrderItem o where o.customer.customerId = :customerId")
	public double findByCustomerId(int customerId);
}