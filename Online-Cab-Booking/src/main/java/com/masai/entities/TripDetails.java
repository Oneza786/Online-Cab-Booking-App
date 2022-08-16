package com.masai.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class TripDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")  //renaming column name to customerId
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "cabDriverId")
	private CabDriver cabDriver;
	
	@NotNull(message = "Pickup Location cannot be null")
	private String fromLocation;
	
	@NotNull(message = "Drop Location cannot be null")
	private String toLocation;
	
	
	private Boolean status = false;
	
	@NotNull(message = "Distance cannot be null")
	private Float distance;
	
	
	private Float totalFare;


	



	public TripDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TripDetails(Integer tripId, Customer customer, CabDriver cabDriver,
			@NotNull(message = "Pickup Location cannot be null") String fromLocation,
			@NotNull(message = "Drop Location cannot be null") String toLocation, Boolean status,
			@NotNull(message = "Distance cannot be null") Float distance, Integer billID, Float totalFare){
		super();
		this.tripId = tripId;
		this.customer = customer;
		this.cabDriver = cabDriver;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.status = status;
		this.distance = distance;
		this.totalFare = totalFare;
	}



	public Integer getTripId() {
		return tripId;
	}


	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public CabDriver getCabDriver() {
		return cabDriver;
	}


	public void setCabDriver(CabDriver cabDriver) {
		this.cabDriver = cabDriver;
	}


	public String getFromLocation() {
		return fromLocation;
	}


	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}


	public String getToLocation() {
		return toLocation;
	}


	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Float getDistance() {
		return distance;
	}


	public void setDistance(Float distance) {
		this.distance = distance;
	}


	public Float getTotalFare() {
		return totalFare;
	}


	public void setTotalFare(Float totalFare) {
		this.totalFare = totalFare;
	}
	
	
	
}
