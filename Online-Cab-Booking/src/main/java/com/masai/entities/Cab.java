package com.masai.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cabId;
	
	@NotNull(message = "Number Plate cannot be null")
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}",message = "Number Plate should be in format of MH23BC5678")
	private String numberPlate;
	
	@NotNull(message = "Car type cannot be null")
	private String carType;
	
	@NotNull(message = "Rate cannot be null")
	private Float ratePerKms;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driverID")
	private CabDriver cabDriver;

	public Integer getCabId() {
		return cabId;
	}

	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Float getRatePerKms() {
		return ratePerKms;
	}

	public void setRatePerKms(Float ratePerKms) {
		this.ratePerKms = ratePerKms;
	}

	public CabDriver getCabDriver() {
		return cabDriver;
	}

	public void setCabDriver(CabDriver cabDriver) {
		this.cabDriver = cabDriver;
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", numberPlate=" + numberPlate + ", carType=" + carType + ", ratePerKms="
				+ ratePerKms + "]";
	}
	
	
}
