package com.masai.services.customer;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.Customer;
import com.masai.exceptions.UserDoesNotExist;
import com.masai.exceptions.UserNameAlreadyExist;
import com.masai.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerDao;
	

	public ResponseEntity<Customer> insertCustomer(Customer customer) {
		Customer cust = customerDao.findByUsername(customer.getUsername());
		if(cust != null) throw new UserNameAlreadyExist("username already exist");
		customerDao.save(customer);
		ResponseEntity<Customer> re = new ResponseEntity<>(customer,HttpStatus.CREATED);
		return re;
	}


	@Override
	public ResponseEntity<Customer> updateCustomer(Customer customer,String user,String pass) {
		// TODO Auto-generated method stub
		Customer cust = customerDao.findByUsernameAndPassword(user, pass);
		if(cust == null) throw new UserDoesNotExist("username or password is wrong");
		
		if(customer.getUsername() != null) {
			Customer cust_new = customerDao.findByUsername(customer.getUsername());
			if(cust_new != null) throw new UserNameAlreadyExist("username already exist");
			cust.setUsername(customer.getUsername());
		}
		if(customer.getPassword() != null) cust.setPassword(customer.getPassword());
		if(customer.getEmail() != null) cust.setEmail(customer.getEmail());
		if(customer.getAddress() != null) cust.setAddress(customer.getAddress());
		if(customer.getMobile() != null) cust.setMobile(customer.getMobile());
		
		customerDao.save(cust);
		ResponseEntity<Customer> re = new ResponseEntity<>(cust,HttpStatus.OK);
		return re;
	}


	@Override
	public ResponseEntity<String> deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer cust = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
		if(cust == null) throw new UserDoesNotExist("username or password is wrong");
		customerDao.delete(cust);
		ResponseEntity<String> re = new ResponseEntity<>("Customer with username : " + customer.getUsername() + " deleted",HttpStatus.OK);
		return re;
	}
}
