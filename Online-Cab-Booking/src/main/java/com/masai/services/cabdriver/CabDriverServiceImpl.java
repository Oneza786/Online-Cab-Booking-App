package com.masai.services.cabdriver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.Cab;
import com.masai.entities.CabDriver;
import com.masai.entities.CabDriverCabDTO;
import com.masai.entities.TripDetails;
import com.masai.exceptions.TripInProgress;
import com.masai.exceptions.UserDoesNotExist;
import com.masai.exceptions.UserNameAlreadyExist;
import com.masai.repository.CabDriverRepository;
import com.masai.repository.CabRepository;


@Service
public class CabDriverServiceImpl implements CabDriverService {

	@Autowired
	private CabDriverRepository cabDriverDao;
	
	@Autowired
	private CabRepository cabDao;
	
	@Override
	public ResponseEntity<CabDriver> insertCabDriver(CabDriverCabDTO cabdto) {
		
		Cab cab = new Cab();
		cab.setCarType(cabdto.getCarType());
		cab.setNumberPlate(cabdto.getNumberPlate());
		cab.setRatePerKms(cabdto.getRatePerKms());
		
		CabDriver cabDriver = new CabDriver();
		cabDriver.setAddress(cabdto.getAddress());
		cabDriver.setUsername(cabdto.getUsername());
		cabDriver.setPassword(cabdto.getPassword());
		cabDriver.setMobile(cabdto.getMobile());
		cabDriver.setEmail(cabdto.getEmail());
		cabDriver.setCab(cab);
		cabDriver.setLicenseNumber(cabdto.getLicenseNumber());
		
		System.out.println(cabDriver);
		cab.setCabDriver(cabDriver);
		
		CabDriver cd = cabDriverDao.findByUsername(cabdto.getUsername());
		CabDriver cd2 = cabDriverDao.findByLicenseNumber(cabdto.getLicenseNumber());
		if(cd != null) throw new UserNameAlreadyExist("Username Already Exist");
		if(cd2 != null) throw new UserNameAlreadyExist("License number already registered");
		
		Cab cb = cabDao.findByNumberPlate(cabdto.getNumberPlate());
		if(cb != null) throw new UserNameAlreadyExist("Number Plate already registered");
		cabDriverDao.save(cabDriver);
		return new ResponseEntity<>(cabDriver,HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<CabDriver> updateCabDriver(CabDriverCabDTO cabdto,String user,String pass) {
		
//		String username = cabdto.getUsername();
		CabDriver cd = cabDriverDao.findByUsernameAndPassword(user,pass);
		
		if(cd == null) throw new UserDoesNotExist("Username or Password is wrong");
		
		Cab cb = cd.getCab();
		System.out.println(cb);
		System.out.println(cabdto);
		
		if(cabdto.getUsername() != null) {
			CabDriver cd2 = cabDriverDao.findByUsername(cabdto.getUsername());
			if(cd2 != null) throw new UserNameAlreadyExist("username already exist");
			cd.setUsername(cabdto.getUsername());
		}
		if(cabdto.getPassword() != null) cd.setPassword(cabdto.getPassword());
		if(cabdto.getMobile() != null) cd.setMobile(cabdto.getMobile());
		if(cabdto.getAddress() != null) cd.setAddress(cabdto.getAddress());
		if(cabdto.getLicenseNumber() != null) {
			CabDriver cd2 = cabDriverDao.findByLicenseNumber(cabdto.getLicenseNumber());
			if(cd2 != null) throw new UserNameAlreadyExist("License number already exist");
		}
		
		if(cabdto.getEmail() != null) cd.setEmail(cabdto.getEmail());
//		if(cabdto.get)
		
		if(cabdto.getCarType() != null) cb.setCarType(cabdto.getCarType());
		if(cabdto.getNumberPlate() != null) {
			Cab cb2 = cabDao.findByNumberPlate(cabdto.getNumberPlate());
			if(cb2 != null) throw new UserNameAlreadyExist("Number Plate already registered");
			cb.setNumberPlate(cabdto.getNumberPlate());;
		}
		if(cabdto.getRatePerKms() != null) cb.setRatePerKms(cabdto.getRatePerKms());
		
		cabDriverDao.save(cd);
		
		
		return new ResponseEntity<CabDriver>(cd,HttpStatus.OK); 
		
	}

	@Override
	public ResponseEntity<String> deleteCabDriver(CabDriver cabDriver) {
		// TODO Auto-generated method stub
		CabDriver cd = cabDriverDao.findByUsernameAndPassword(cabDriver.getUsername(), cabDriver.getPassword());
		if(cd == null) throw new UserDoesNotExist("username or password is wrong");
		cabDriverDao.delete(cd);
		return new ResponseEntity<>("driver with username : " + cabDriver.getUsername() + " deleted" ,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateStatus(CabDriver cabDriver) {
		
		CabDriver cd = cabDriverDao.findByUsernameAndPassword(cabDriver.getUsername(), cabDriver.getPassword());
		if(cd == null) throw new UserDoesNotExist("username or password is wrong");
		List<TripDetails> tripList = cd.getTripDetailsList();
		
		if(tripList.size() > 0) {
			TripDetails lasTripDetails = tripList.get(tripList.size()-1);
			if(lasTripDetails.getStatus() == false) throw new TripInProgress("Trip is already in progress");
			
		}
		cd.setAvailablity(!cd.getAvailablity());
		cabDriverDao.save(cd);
		return new ResponseEntity<>("Status Updated Successfully",HttpStatus.ACCEPTED);
	}
	
	
	
}
