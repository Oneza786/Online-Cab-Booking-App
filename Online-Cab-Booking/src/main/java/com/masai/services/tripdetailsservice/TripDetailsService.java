package com.masai.services.tripdetailsservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.masai.entities.BillDetails;
import com.masai.entities.TripDetails;
import com.masai.entities.TripDetailsDTO;

public interface TripDetailsService {

	public ResponseEntity<TripDetails> insertTripDetails(TripDetailsDTO tripDto);
	
	public ResponseEntity<String> deleteBookedTrip(TripDetailsDTO tripDto);
	
	public ResponseEntity<List<TripDetails>> getAllTripsOfCustomer(TripDetailsDTO tripDto);
	
	public ResponseEntity<String> calculateBill(TripDetailsDTO tripDto);
	
	public ResponseEntity<BillDetails> generateBill(TripDetailsDTO tripDto);
}
