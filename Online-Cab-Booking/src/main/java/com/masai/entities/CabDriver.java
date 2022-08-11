package com.masai.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CabDriver extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	@OneToOne
	private Cab cab;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<TripDetails> tripDetailsList = new ArrayList<>();

	public List<TripDetails> getTripDetailsList() {
		return tripDetailsList;
	}

	public void setTripDetailsList(List<TripDetails> tripDetailsList) {
		this.tripDetailsList = tripDetailsList;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public CabDriver(Integer driverId, Cab cab) {
		super();
		this.driverId = driverId;
		this.cab = cab;
	}
	
	public CabDriver() {
		// TODO Auto-generated constructor stub
	}
}
