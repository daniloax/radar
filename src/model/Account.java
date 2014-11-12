package model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private int accountNumber;
	private int password;
	
	private Cell cell;
	
	public Account() {
		cell = new Cell();
	}
	
	public Account(int accountNumber, int password, String name, double longitude, double latitude) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.cell.setName(name);
		this.cell.getPosition().x.setValue(longitude);
		this.cell.getPosition().y.setValue(latitude);
	}
	
	public Account( int accountNumber, int password, String name, Position<Longitude, Latitude> position) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.cell.setName(name);
		this.cell.setPosition(position);
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
		return cell.getLongitude().getValue();
	}

	public void setLongitude(double longitude) {
		this.cell.getLongitude().setValue(longitude);
	}

	public double getLatitude() {
		return cell.getLatitude().getValue();
	}

	public void setLatitude(double latitude) {
		this.cell.getLatitude().setValue(latitude);
	}

	public Position<Longitude, Latitude> getPosition() {
		return cell.getPosition();
	}
	
	public void setPosition(Position<Longitude, Latitude> position) {
		this.cell.setPosition(position);
	}
	
	public void setPosition(Double longitude, Double latitude) {
		if (longitude != null)
			this.cell.getPosition().x.setValue(longitude);
		if (latitude != null)
			this.cell.getPosition().y.setValue(latitude);
	}
	
	public boolean validatePassword(int password) {
		if (password == this.password)
			return true;
		else
			return false;
	}

}
