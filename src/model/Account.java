package model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private int accountNumber;
	private String user;
	private int password;
	private Longitude longitude;
	private Latitude latitude;
	private Position<Longitude, Latitude> position;
	
	public Account() {
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
	}
	
	public Account(int accountNumber, String user, int password, double latitude, double longitude) {
		this.accountNumber = accountNumber;
		this.user = user;
		this.password = password;
		this.position = new Position<Longitude, Latitude>(new Longitude(longitude), new Latitude(latitude));
	}
	
	public Account( int accountNumber , String user , int password, Position<Longitude, Latitude> position) {
		this.accountNumber = accountNumber;
		this.user = user;
		this.password = password;
		this.position = position;
	}

	public int getAccount() {
		return accountNumber;
	}

	public void setAccount(int accountNumber) {
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
	
	public double getLongitude() {
		return longitude.getValue();
	}

	public void setLongitude(double longitude) {
		this.longitude.setValue(longitude);
	}

	public double getLatitude() {
		return latitude.getValue();
	}

	public void setLatitude(double latitude) {
		this.latitude.setValue(latitude);
	}

	public Position<Longitude, Latitude> getPosition() {
		return position;
	}
	
	public void setPosition(Position<Longitude, Latitude> position) {
		this.position = position;
	}
	
	public void setPosition(Double longitude, Double latitude) {
		if (longitude != null)
			this.position.x.setValue(longitude);
		if (latitude != null)
			this.position.y.setValue(latitude);
	}
	
	public boolean validatePassword(int password) {
		if (password == this.password)
			return true;
		else
			return false;
	}

}
