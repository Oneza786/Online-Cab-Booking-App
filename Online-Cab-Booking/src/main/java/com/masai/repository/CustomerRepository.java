package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer findByUsername(String username);
	public Customer findByUsernameAndPassword(String username,String password);
	

}
