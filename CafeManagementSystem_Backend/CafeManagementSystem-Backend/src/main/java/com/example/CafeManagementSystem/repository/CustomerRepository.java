package com.example.CafeManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CafeManagementSystem.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	@Query("select c from Customer c where c.emailId = :emailId and c.password = :password")
	public Customer findByEmailIdAndPassword(String emailId, String password);


    // Find customer by email
    Optional<Customer> findByEmailId(String emailId);
	
	
}
