package model;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private boolean on;
	private double radius;
	private int row;
	private int column;
	
	private Longitude longitude;
	private Latitude latitude;
	private Position<Longitude, Latitude> position;
	private Position<Integer, Integer> square;
	private String name;
	
	private static final int UNIT = 15;
	
	public Cell() {
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
		square = new Position<Integer, Integer>(column, row);
	}
	
	public Cell(String name) {
		this.name = name;
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
		square = new Position<Integer, Integer>(column, row);
	}
	
	public Cell(String name, Longitude longitude, Latitude latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		position = new Position<Longitude, Latitude>(longitude, latitude);
		setSquare();
	}
	
	public Cell(String name, Position<Longitude, Latitude> position) {
		this.name = name;
		this.longitude.setValue(position.x.getValue());
		this.latitude.setValue(position.y.getValue());
		this.position = position;
		setSquare();
	}
	
	public boolean isOn() {
		return on;
	}
	
	public void setOff() {
		this.on = false;
	}
	
	public void setOn() {
		this.on = true;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Longitude getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Longitude longitude) {
		this.longitude = longitude;
		this.position.x.setValue(longitude.getValue());
	}
	
	public Latitude getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Latitude latitude) {
		this.latitude = latitude;
		this.position.y.setValue(latitude.getValue());
	}
	
	public Position<Longitude, Latitude> getPosition() {
		return position;
	}
	
	public void setPosition(Position<Longitude, Latitude> position) {
		this.longitude.setValue(position.x.getValue());
		this.latitude.setValue(position.y.getValue());
		this.position = position;
	}
	
	public Position<Integer, Integer> getSquare() {
		return square;
	}
	
	public void setSquare() {
		column = longitude.getValue() < 0 ? (int) (longitude.getValue() / (double) UNIT) - 1 : (int) (longitude.getValue() / (double) UNIT) + 1;
		row = latitude.getValue() < 0 ? (int) (latitude.getValue() / (double) UNIT) - 1 : (int) (latitude.getValue() / (double) UNIT) + 1;
		square.x = column;
		square.y = row;
	}
	
	public void setSquare(Position<Integer, Integer> square) {
		this.square = square;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = (radius > 0.0 ? radius : 0.0);
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public String toString() {
		return String.format("\n%s\n\n%-17s%9s%12s%10s%10s\n%-17s%9.4f%12.4f%10.2f%7d,%2d\n",
			"Cell View", "Name", "Longitude", "Latitude", "Radius", "Square",
			this.name, this.longitude.getValue(), this.latitude.getValue(), this.radius, this.square.x, this.square.y);
	}

}
