package com.masai.entities;

public class TripDetailsDTO {
	
	private Integer tripId;
	private String username;
	private String password;
	private String fromLocation;
	private String toLocation;
	private Float distance;
	
	
	
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "TripDetailsDTO [username=" + username + ", password=" + password + ", fromLocation=" + fromLocation
				+ ", toLocation=" + toLocation + "]";
	}

	

}
