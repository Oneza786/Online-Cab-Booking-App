package com.masai.entities;

public class CabDriverCabDTO extends User{
	private String licenseNumber;
	
	private String numberPlate;
	
	private Float ratePerKms;
	
	private String carType;
	
	public CabDriverCabDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CabDriverCabDTO(String licenseNumber, String numberPlate, Float ratePerKms, String carType) {
		super();
		this.licenseNumber = licenseNumber;
		this.numberPlate = numberPlate;
		this.ratePerKms = ratePerKms;
		this.carType = carType;
	}



	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public Float getRatePerKms() {
		return ratePerKms;
	}

	public void setRatePerKms(Float ratePerKms) {
		this.ratePerKms = ratePerKms;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}



	@Override
	public String toString() {
		return "CabDriverCabDTO [licenseNumber=" + licenseNumber + ", numberPlate=" + numberPlate + ", ratePerKms="
				+ ratePerKms + ", carType=" + carType + "]";
	}
	
	
}
