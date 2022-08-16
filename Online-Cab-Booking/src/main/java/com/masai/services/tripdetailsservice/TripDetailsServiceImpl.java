package com.masai.services.tripdetailsservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.BillDetails;
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
		
		if(tripDetailsList.size() > 0) {
			if(tripDetailsList.get(tripDetailsList.size()-1).getStatus()== false) throw new TripInProgress("Cannot Book another ... As trip is already in Progress");
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
         float distance = (float)Math.floor(Math.random()*(max-min+1)+min); //setting distance using random variable
         tripDetails.setDistance(distance);
         
         
         
         
		tripDetailsDao.save(tripDetails);
		
		return new ResponseEntity<>(tripDetails,HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<String> deleteBookedTrip(TripDetailsDTO tripDto) { //deleting booked trip which has not yet started

		Customer cus = customerDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
		if(cus == null) throw new UserDoesNotExist("user name or password is wrong");
		
		List<TripDetails> tripDetailsList = cus.getTripDetailsList();
		
		if(tripDetailsList.size() > 0) {
			if(tripDetailsList.get(tripDetailsList.size()-1).getStatus()== false) {
				CabDriver cabDriver = tripDetailsList.get(tripDetailsList.size()-1).getCabDriver();
				cabDriver.setAvailablity(true);
				cabDriverDao.save(cabDriver);
				tripDetailsList.remove(tripDetailsList.size()-1);
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

	@Override
	public ResponseEntity<String> calculateBill(TripDetailsDTO tripDto) {
	
		CabDriver cabDriver = cabDriverDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
		
		if(cabDriver == null) throw new UserDoesNotExist("user name or password is wrong");
		
		List<TripDetails> customerTripList = cabDriver.getTripDetailsList();
		
		if(customerTripList.size() == 0) throw new TripInProgress("No Active Trip Found");
		
		
		
		TripDetails lastTripDetail =   customerTripList.get(customerTripList.size()-1);
		
		if(lastTripDetail.getStatus()==true) throw new TripInProgress("All Trips Completed");
		
		Float ratePerkms = cabDriver.getCab().getRatePerKms();
		Float distance = lastTripDetail.getDistance();
		
		lastTripDetail.setTotalFare(ratePerkms*distance);
		cabDriver.setAvailablity(true);
		lastTripDetail.setStatus(true);
		cabDriverDao.save(cabDriver);
		
//		tripDetailsDao.save(lastTripDetail);
		
		return new ResponseEntity<>("Bill is " + lastTripDetail.getTotalFare(),HttpStatus.ACCEPTED); 
		
	}

	@Override
	public ResponseEntity<BillDetails> generateBill(TripDetailsDTO tripDto) {//bean class BillDetails
		
		Customer cus = customerDao.findByUsernameAndPassword(tripDto.getUsername(), tripDto.getPassword());
		if(cus == null) throw new UserDoesNotExist("user name or password is wrong");
		
		TripDetails tripDetails = tripDetailsDao.findById(tripDto.getTripId()).get(); 
		if(tripDetails == null) throw new TripInProgress("Trip with given id does not exist");
		
		if(cus.getUsername().equals(tripDetails.getCustomer().getUsername())) {
			if(tripDetails.getStatus()== false) throw new TripInProgress("trip not completed yet");
			
			BillDetails billDetails = new BillDetails();
			billDetails.setDistance(tripDetails.getDistance());
			billDetails.setRatePerKms(tripDetails.getCabDriver().getCab().getRatePerKms());
			billDetails.setTotalBill(tripDetails.getTotalFare());
			
			return new ResponseEntity<>(billDetails,HttpStatus.ACCEPTED);
	
		}
		
		throw new UserDoesNotExist("User not Verified");
	}

	
	
	
}
