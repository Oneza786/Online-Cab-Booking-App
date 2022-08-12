package com.masai.services.tripdetailsservice;

import org.springframework.http.ResponseEntity;

import com.masai.entities.TripDetails;
import com.masai.entities.TripDetailsDTO;

public interface TripDetailsService {

	public ResponseEntity<TripDetails> insertTripDetails(TripDetailsDTO tripDto);
	
	public ResponseEntity<String> deleteBookedTrip(TripDetailsDTO tripDto);
}
