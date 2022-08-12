package com.masai.services.cabdriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.Cab;
import com.masai.entities.CabDriver;
import com.masai.entities.CabDriverCabDTO;
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
	
	
	
}
