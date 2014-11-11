package model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private int accountNumber;
	private int password;
	
	private Cell cell;
	private Longitude longitude;
	private Latitude latitude;
	private Position<Longitude, Latitude> position;
	
	public Account() {
		cell = new Cell();
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
	}
	
	public Account(int accountNumber, int password, String name, double longitude, double latitude) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.cell.setName(name);
		this.position.x.setValue(longitude);
		this.position.y.setValue(latitude);
	}
	
	public Account( int accountNumber, int password, String name, Position<Longitude, Latitude> position) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.cell.setName(name);
		this.position = position;
	}

	public int getAccount() {
		return accountNumber;
	}

	public void setAccount(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
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
