package com.example.CafeManagementSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customer_table")
@SequenceGenerator(name = "generator1", sequenceName = "gen1", initialValue = 100)
public class Customer {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator1")
//	@Column(name = "customer_id")
//	private int customerId;
//	private String firstName;
//	private String lastName;
//	private String dateOfBirth;
//	private String phoneNo;
//	private String gender;
//	private String address;
//	private String distict;
//	private String state;
//	private String zipCode;
//	private String emailId;
//	private String password;
//	private String role;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator1")
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="first_name" ,length=20)
	@NotEmpty
	@Size(min=3 , message="firstName must contain atleast 3 characters")
	private String firstName;
	
	@Column(name="last_name" ,length=20)
	@NotEmpty
	@Size(min=2 , message="lastName must contain atleast 2 characters")
	private String lastName;
	
	@Column(name="date_of_birth" ,length=20)
	@NotEmpty
	private String dateOfBirth;
	
	@Column(name="phone_number")
	@NotEmpty
	@Size(min=10 ,max=10, message="phoneNumber must contain  10 digits")
	private String phoneNo;
	
	@Column(name="gender")
	@NotEmpty
	private String gender;
	
	@Column(name="address")
	@NotEmpty(message = "Address can not be empty")
	//@Size(min=3 , message="district must contain atleast 3 characters")
    	private String address;
		
	@Column(name="district",length=20)
	@NotEmpty
	@Size(min=3 , message="district must contain atleast 3 characters")
    	private String distict;
	
	@Column(name="state",length=20)
	@NotEmpty
	@Size(min=3 , message="district must contain atleast 3 characters")
    	private String state;
	
	@Column(name="zip_code")
	@NotEmpty
	@Size(min=6 ,max=6, message="zipCode must contain 6 digits")
	private String zipCode;
	
	@Column(name="email_id",unique=true,length=30)
	@NotEmpty
	@Email(message="Email  is not valid!")
	public String emailId;
	
	@Column(name="password",length=20)
	@NotEmpty
	@Size(min=8, message="Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	public String password;
	
	public String role;
	
	public Customer() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistict() {
		return distict;
	}

	public void setDistict(String distict) {
		this.distict = distict;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", phoneNo=" + phoneNo + ", gender=" + gender + ", address="
				+ address + ", distict=" + distict + ", state=" + state + ", zipCode=" + zipCode + ", emailId="
				+ emailId + ", password=" + password + ", role=" + role + "]";
	}

	

}

/*
 {
 "firstName":"Sandhesh",
 "lastName":"V",
 "dateOfBirth":"2001-08-15",
 "phoneNo":"2343267185",
 "gender":"male",
 "address":"RR nagara",
 "distict":"Hassan",
 "state":"Karnataka",
 "zipCode":"560091",
 "emailId":"sandhesh@gmail.com",
 "password":"sandy@123",
 "role":"user"
}
*/
