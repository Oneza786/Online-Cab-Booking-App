package com.masai.services.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.Admin;
import com.masai.entities.TripDetails;


@Service
public interface AdminService {
	
	public ResponseEntity<Admin> insertAdmin(Admin admin);
	
	public ResponseEntity<Admin> updateAdmin(Admin admin, String user, String pass);
	
	public ResponseEntity<String> deleteAdmin(Admin admin);
	
	public ResponseEntity<List<TripDetails>> getAllTrips(Admin admin);
	
	public ResponseEntity<List<TripDetails>> getAllTripsByCab(Admin admin, Integer cabId);
	
	public ResponseEntity<List<TripDetails>> getAllTripsByCustomer(Admin admin, String username);
}
