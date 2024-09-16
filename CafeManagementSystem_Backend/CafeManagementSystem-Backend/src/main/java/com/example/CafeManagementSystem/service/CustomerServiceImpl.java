package com.example.CafeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.exception.CustomerNotFoundException;
import com.example.CafeManagementSystem.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer insertCustomer(Customer customer) {

		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomerRecord() {

		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException {

		Optional<Customer> result = customerRepository.findById(customerId);
		Customer customer;
		if(result.isPresent())
		{
			customer = result.get();
		}
		else
		{
			throw new CustomerNotFoundException("Customer Id not found : "+customerId);
		}
		
		return customer;
	}

	@Override
	public Customer updateCustomerById(Customer customer, int customerId) throws CustomerNotFoundException {

		Optional<Customer> result = customerRepository.findById(customerId) ;
		Customer existingCustomer;
		if(result.isPresent())
		{
			existingCustomer = result.get();
		}
		else
		{
			throw new CustomerNotFoundException("Customer Id not found : "+customerId);
		}
		
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setDateOfBirth(customer.getDateOfBirth());
		existingCustomer.setPhoneNo(customer.getPhoneNo());
		existingCustomer.setGender(customer.getGender());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setDistict(customer.getDistict());
		existingCustomer.setState(customer.getState());
		existingCustomer.setZipCode(customer.getZipCode());
		existingCustomer.setEmailId(customer.getEmailId());
		existingCustomer.setPassword(customer.getPassword());
		
		customerRepository.save(existingCustomer);
		return existingCustomer;
	}

	@Override
	public void deleteCustomerById(int customerId) throws CustomerNotFoundException {

		if(customerRepository.existsById(customerId))
		{
			customerRepository.deleteById(customerId);
		}
		else
		{
			throw new CustomerNotFoundException("Customer with id : "+customerId+" not found.");
		}
	}

	
	@Override
	public Customer loginCustomer(Customer customer) throws CustomerNotFoundException {

		
		Customer result =  customerRepository.findByEmailIdAndPassword(customer.getEmailId(), customer.getPassword());
		
		System.out.println("////////////////////////////////////////////////////////////////"+ result+"//////////////////////////////////////////////////////////////////");
		
		Customer existingCustomer;
		if(result != null)
		{
			existingCustomer = result;
		}
		else
		{
			throw new CustomerNotFoundException("Customer emailId not found : "+customer.emailId);
		}
		
		return existingCustomer;
	}
	
	 
}
