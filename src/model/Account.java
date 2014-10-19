package model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private int accountNumber;
	private String user;
	private int password;
	private double latitude;
	private double longitude;
	
	public Account( int accountNumber , String user , int password, double latitude, double longitude ) {
		this.accountNumber = accountNumber;
		this.user = user;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public boolean validatePassword(int password) {
		if (password == this.password)
			return true;
		else
			return false;
	}
	
	public void updatePosition(Double latitude, Double longitude) {
		if (latitude != null)
			this.latitude = latitude;
		if (longitude != null)
			this.longitude = longitude;
	}

}
