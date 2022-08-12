package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.CabDriver;

@Repository
public interface CabDriverRepository extends JpaRepository<CabDriver, Integer> {

	public CabDriver findByUsername(String username);
	
	public CabDriver findByLicenseNumber(String license);
	
	public CabDriver findByUsernameAndPassword(String user,String pass);
}
