package com.masai.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CabDriver extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	
	
	@OneToOne
	private Cab cab;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cabDriver")
	List<TripDetails> tripDetailsList = new ArrayList<>();

	
	
	@NotNull(message = "License Number cannot be null")
	@Column(unique = true)
	private String licenseNumber;
	
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

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
