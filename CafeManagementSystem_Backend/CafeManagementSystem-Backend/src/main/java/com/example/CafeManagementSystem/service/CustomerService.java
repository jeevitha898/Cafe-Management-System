package com.example.CafeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import com.example.CafeManagementSystem.entity.Customer;

public interface CustomerService {

	public Customer insertCustomer(Customer customer);
	public List<Customer> getAllCustomerRecord();
	public Customer getCustomerById(int customerId);
	public Customer updateCustomerById(Customer customer, int customerId);
	public void deleteCustomerById(int customerId);
	public Customer loginCustomer(Customer customer);
}
