package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.Admin;
import com.masai.entities.CabDriver;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Admin findByUsername(String username);
	
	public Admin findByUsernameAndPassword(String user,String pass);
}
