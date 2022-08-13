package com.masai.services.tripdetailsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.CabDriver;
import com.masai.entities.Customer;
import com.masai.entities.TripDetails;
import com.masai.entities.TripDetailsDTO;
import com.masai.exceptions.CabDriverNotAvailableException;
import com.masai.exceptions.TripInProgress;
import com.masai.exceptions.UserDoesNotExist;
import com.masai.repository.CabDriverRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.TripDetailsRepository;



@Service
public class TripDetailsServiceImpl implements TripDetailsService{

	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private CabDriverRepository cabDriverDao;
	
	@Autowired
	private TripDetailsRepository tripDetailsDao;
	
	@Override
	public ResponseEntity<TripDetails> insertTripDetails(TripDetailsDTO tripDto) {
		
		Customer cus= customerDao.findByUsernameAndPassword(tripDto.getUsername(),tripDto.getPassword());
		if(cus == null) throw new UserDoesNotExist("user name or password is wrong");
		
		List<TripDetails> tripDetailsList =  cus.getTripDetailsList();
		
		for(int i=0;i<tripDetailsList.size();i++) {
			if(tripDetailsList.get(i).getStatus()== false) throw new TripInProgress("Cannot Book another ... As trip is already in Progress");
			
			
		}
		
		TripDetails tripDetails = new TripDetails();
		
		tripDetails.setCustomer(cus);
		
		
		List<CabDriver> cabDriverList = cabDriverDao.findAll();
		
		CabDriver cabDriver = null;
		
		for(int i=0;i<cabDriverList.size();i++) {
			if(cabDriverList.get(i).getAvailablity()==true) 
			{
				cabDriver = cabDriverList.get(i);
				break;
			}
		}
		
		if(cabDriver==null) throw new CabDriverNotAvailableException("No Driver Available at the moment");
		
		tripDetails.setCabDriver(cabDriver);
		cabDriver.getTripDetailsList().add(tripDetails);
		cabDriver.setAvailablity(false);
		cus.getTripDetailsList().add(tripDetails);
		tripDetails.setFromLocation(tripDto.getFromLocation());
		tripDetails.setToLocation(tripDto.getToLocation());
//         tripDetails.setDistance(tripDto.getDistance());
         
        
         int min = 10;
         int max = 100;
         float distance = (float)Math.floor(Math.random()*(max-min+1)+min);
         tripDetails.setDistance(distance);
         
         
         
         
		tripDetailsDao.save(tripDetails);
		
		return new ResponseEntity<>(tripDetails,HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<String> deleteBookedTrip(TripDetailsDTO tripDto) {

		Customer cus = customerDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
		if(cus == null) throw new UserDoesNotExist("user name or password is wrong");
		
		List<TripDetails> tripDetailsList = cus.getTripDetailsList();
		
		for(int i=0;i<tripDetailsList.size();i++) {
			if(tripDetailsList.get(i).getStatus()== false) {
				CabDriver cab = tripDetailsList.get(i).getCabDriver();
				cab.setAvailablity(true);
				cabDriverDao.save(cab);
				tripDetailsList.remove(i);
				customerDao.save(cus);
				return new ResponseEntity<>("Trip cancelled successfully",HttpStatus.ACCEPTED);
			}
		}
		
		return new ResponseEntity<>("No active trip found",HttpStatus.NOT_FOUND);
		
		
		
	}

	@Override
	public ResponseEntity<List<TripDetails>> getAllTripsOfCustomer(TripDetailsDTO tripDto) {
		
		Customer cus = customerDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
		
		if(cus ==null) throw new UserDoesNotExist("customer does not exist");
		
		List<TripDetails> customerTripList = cus.getTripDetailsList();
		
		return new ResponseEntity<>(customerTripList , HttpStatus.OK);
		
		
	}

//	@Override
//	public ResponseEntity<String> calculateBill(TripDetailsDTO tripDto) {
//	
//		Customer cus = customerDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
//		
//		if(cus == null) throw new UserDoesNotExist("user name or password is wrong");
//		
//	
//		
//		
//		
//	}

	
	
	
}
