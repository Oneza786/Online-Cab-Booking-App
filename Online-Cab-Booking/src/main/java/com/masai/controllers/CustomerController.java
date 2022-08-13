package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entities.BillDetails;
import com.masai.entities.Customer;
import com.masai.entities.TripDetails;
import com.masai.entities.TripDetailsDTO;
import com.masai.services.customer.CustomerServiceImpl;
import com.masai.services.tripdetailsservice.TripDetailsService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl custService;
	
	@Autowired
	private TripDetailsService tripService;
	
	@PostMapping("/create")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		return custService.insertCustomer(customer);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestParam String user,@RequestParam String pass,@RequestBody Customer customer) {
		return custService.updateCustomer(customer, user, pass);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
		return custService.deleteCustomer(customer);
	}
	
	@PostMapping("/booktrip")
	public ResponseEntity<TripDetails> bookTrip(@RequestBody TripDetailsDTO tripDto){
		return tripService.insertTripDetails(tripDto);
	}
	
	@DeleteMapping("/canceltrip")
	public ResponseEntity<String> deleleTrip(@RequestBody TripDetailsDTO tripDto){
		return tripService.deleteBookedTrip(tripDto);
	}
	
	@PostMapping("/triplist")
	public ResponseEntity<List<TripDetails>> getAllCustomerTripList(@RequestBody TripDetailsDTO tripDto){
		
		return tripService.getAllTripsOfCustomer(tripDto);
	}
	
	@PostMapping("/generatebill")
	public ResponseEntity<BillDetails> generateBillHandler(@RequestBody TripDetailsDTO tripDto){
		return tripService.generateBill(tripDto);
	}
}
