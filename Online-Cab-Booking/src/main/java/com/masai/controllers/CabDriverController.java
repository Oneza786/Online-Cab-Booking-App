package com.masai.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.masai.entities.CabDriver;
import com.masai.entities.CabDriverCabDTO;
import com.masai.entities.TripDetailsDTO;
import com.masai.services.cabdriver.CabDriverService;
import com.masai.services.tripdetailsservice.TripDetailsService;

@RestController
@RequestMapping(value = "/cabdriver")
public class CabDriverController {

	@Autowired
	private CabDriverService cabDriverService;
	
	@Autowired
	private TripDetailsService tripDetailsService;
	
	@PostMapping("/create")
	public ResponseEntity<CabDriver> insertCabDriverHandler(@RequestBody CabDriverCabDTO cabdto){
		
		return cabDriverService.insertCabDriver(cabdto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CabDriver> updateCabDriverHandler(@RequestBody CabDriverCabDTO cabdto,@RequestParam String user,@RequestParam String pass){
		
		return cabDriverService.updateCabDriver(cabdto,user,pass);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCabDriverHandler(@RequestBody CabDriver cabDriver){
		return cabDriverService.deleteCabDriver(cabDriver);
	}
	
	@PostMapping("/tripcompleted")
	public ResponseEntity<String> tripCompletionHandler(@RequestBody TripDetailsDTO tripDto){
		return tripDetailsService.calculateBill(tripDto);
	}
	
	@PostMapping("/updatestatus")
	public ResponseEntity<String> updateStatusHandler(@RequestBody CabDriver cDriver){
		return cabDriverService.updateStatus(cDriver);
	}
}
