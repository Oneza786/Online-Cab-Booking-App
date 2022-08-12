package com.masai.exceptions;

public class TripInProgress extends RuntimeException{

	public TripInProgress() {
		
	}
	public TripInProgress(String msg) {
		super(msg);
	}
}
