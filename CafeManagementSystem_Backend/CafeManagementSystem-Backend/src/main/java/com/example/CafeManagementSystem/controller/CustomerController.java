package com.example.CafeManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CafeManagementSystem.entity.Customer;
import com.example.CafeManagementSystem.repository.CustomerRepository;
import com.example.CafeManagementSystem.service.CustomerServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerController(CustomerServiceImpl customerServiceImpl) {
		super();
		this.customerServiceImpl = customerServiceImpl;
	}


	@PostMapping("/register")
	public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer) {

		return new ResponseEntity<Customer>(customerServiceImpl.insertCustomer(customer), HttpStatus.CREATED);
	}

	
	@PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody Customer customer)
	{
		return new ResponseEntity<Customer>(customerServiceImpl.loginCustomer(customer), HttpStatus.OK);
	}
	
	@GetMapping
	public List<Customer> getAllCustomerRecord()
	{
		return customerServiceImpl.getAllCustomerRecord();
	}
	
	
	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId)
	{
		return customerServiceImpl.getCustomerById(customerId);
	}
	
	
	@PutMapping("/customer/{customerId}")
	public Customer updateCustomerById(@PathVariable int customerId, @RequestBody Customer customer)
	{
		return customerServiceImpl.updateCustomerById(customer, customerId);
	}
	
	
	@DeleteMapping("/customer/{customerId}")
	public String deleteById(@PathVariable int customerId)
	{
		customerServiceImpl.deleteCustomerById(customerId);
		return "Deleted successfully with id : "+customerId;
	}
	
	
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<Customer> getEmailById(@RequestBody Customer customer) 
	{	
		return new ResponseEntity<Customer>(customerRepository.findByEmailId(customer.getEmailId()).get(), HttpStatus.OK);
		
	}
	
	
	@PostMapping("/{customerId}/{newpassword}")
	public Customer changePassword(@PathVariable int customerId, @PathVariable String newpassword)
	{
	    Customer c = customerServiceImpl.getCustomerById(customerId);
	    c.setPassword(newpassword);
	    customerServiceImpl.updateCustomerById(c, customerId);
	    return c;
	}
	
	@GetMapping("/customer-by-email/{emailId}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String emailId) {
	    Optional<Customer> customer = customerRepository.findByEmailId(emailId);
	    if (customer.isPresent()) {
	        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if customer not found
	    }
	}

}



