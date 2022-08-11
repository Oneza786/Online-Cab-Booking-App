package com.masai.entities;

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
	@Pattern(regexp = "[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}",message = "Number Plate should be in format of MH23BC5678")
	private String numberPlate;
	
	@NotNull(message = "Car type cannot be null")
	private String carType;
	
	@NotNull(message = "Rate cannot be null")
	private Integer ratePerKms;
	
	@OneToOne
	@JoinColumn(name = "driverID")
	private CabDriver cabDriver;
}
