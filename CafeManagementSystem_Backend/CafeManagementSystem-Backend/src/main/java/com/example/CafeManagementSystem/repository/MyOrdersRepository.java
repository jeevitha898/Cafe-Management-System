package com.example.CafeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CafeManagementSystem.entity.MyOrders;

@Repository
public interface MyOrdersRepository extends JpaRepository<MyOrders, Integer> {

	@Query("select p from MyOrders p where p.customer.customerId = :customerId")
	public List<MyOrders> findByCustomerId(int customerId);
}
