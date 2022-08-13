package com.masai.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.masai.entities.Admin;
import com.masai.entities.Cab;
import com.masai.entities.CabDriver;
import com.masai.entities.Customer;
import com.masai.entities.TripDetails;
import com.masai.exceptions.UserDoesNotExist;
import com.masai.exceptions.UserNameAlreadyExist;
import com.masai.repository.AdminRepository;
import com.masai.repository.CabDriverRepository;
import com.masai.repository.CabRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.TripDetailsRepository;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminDao;
	
	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private CabDriverRepository cabDriverDao;
	
	@Autowired
	private TripDetailsRepository tripDetailsDao;
	
	@Autowired
	private CabRepository cabDao;
	
	
	@Override
	public ResponseEntity<Admin> insertAdmin(Admin admin) {
		Admin adm=adminDao.findByUsername(admin.getUsername());
		if(adm!=null) throw new UserNameAlreadyExist("Useername already Exist");
		adminDao.save(admin);
		ResponseEntity<Admin> re=new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
		return re;
	}

	@Override
	public ResponseEntity<Admin> updateAdmin(Admin admin, String user, String pass) {
		Admin adm=adminDao.findByUsernameAndPassword(user, pass);
		
		if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
		
		if(admin.getUsername() != null) {
			Admin adm_new = adminDao.findByUsername(admin.getUsername());
			if(adm_new != null) throw new UserNameAlreadyExist("username already exist");
			adm.setUsername(admin.getUsername());
		}
		if(admin.getPassword() != null) adm.setPassword(admin.getPassword());
		if(admin.getEmail() != null) adm.setEmail(admin.getEmail());
		if(admin.getAddress() != null) adm.setAddress(admin.getAddress());
		if(admin.getMobile() != null) adm.setMobile(admin.getMobile());
		
		adminDao.save(adm);
		ResponseEntity<Admin> re = new ResponseEntity<>(adm,HttpStatus.OK);
		return re;
	}

	@Override
	public ResponseEntity<String> deleteAdmin(Admin admin) {
		Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
		adminDao.delete(adm);
		ResponseEntity<String> re=new ResponseEntity<>("Admin with username : "+admin.getUsername()+" deleted",HttpStatus.OK);
		return re;
	}

	@Override
	public ResponseEntity<List<TripDetails>> getAllTrips(Admin admin) {
//		System.out.println(admin.getUsername());
//		System.out.println(admin.getPassword());
		Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		
		if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
		
		List<TripDetails> allTrips=tripDetailsDao.findAll();
		ResponseEntity<List<TripDetails>> re=new ResponseEntity<>(allTrips,HttpStatus.OK);
		return re;
	}

	@Override
	public ResponseEntity<List<TripDetails>> getAllTripsByCab(Admin admin, Integer cabId) {
		Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
		
		Cab cab=cabDao.findById(cabId).get();
		if(cab==null) throw new UserDoesNotExist("Cab Does not Exist");
		List<TripDetails> allTripsByCab=cab.getCabDriver().getTripDetailsList();
		ResponseEntity<List<TripDetails>> re=new ResponseEntity<>(allTripsByCab, HttpStatus.OK);
		return re;
	}

	@Override
	public ResponseEntity<List<TripDetails>> getAllTripsByCustomer(Admin admin, String username) {
		Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
		
		Customer cust=customerDao.findByUsername(username);
		if(cust==null) throw new UserDoesNotExist("Customer does not Exist");
		List<TripDetails> allTripsByCustomer=cust.getTripDetailsList();
		ResponseEntity<List<TripDetails>> re=new ResponseEntity<>(allTripsByCustomer,HttpStatus.OK);
		return re;
	}
	
	

}
