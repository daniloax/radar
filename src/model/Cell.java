package model;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private boolean on;
	private double radius;
	
	private int x;
	private int y;
	
	private Longitude longitude;
	private Latitude latitude;
	private Position<Longitude, Latitude> position;
	private Position<Integer, Integer> matrix;
	private String name;
	
	private static final int UNIT = 15;
	
	public Cell() {
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
		matrix = new Position<Integer, Integer>(x, y);
	}
	
	public Cell(String name) {
		this.name = name;
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
		matrix = new Position<Integer, Integer>(x, y);
	}
	
	public Cell(String name, Longitude longitude, Latitude latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		position = new Position<Longitude, Latitude>(longitude, latitude);
		setMatrix();
	}
	
	public Cell(String name, Position<Longitude, Latitude> position) {
		this.name = name;
		this.longitude.setValue(position.x.getValue());
		this.latitude.setValue(position.y.getValue());
		this.position = position;
		setMatrix();
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
	
	public Position<Integer, Integer> getMatrix() {
		return matrix;
	}
	
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public void setMatrix() {
		x = longitude.getValue() < 0 ? (int) (longitude.getValue() / (double) UNIT) - 1 : (int) (longitude.getValue() / (double) UNIT) + 1;
		y = latitude.getValue() < 0 ? (int) (latitude.getValue() / (double) UNIT) - 1 : (int) (latitude.getValue() / (double) UNIT) + 1;
		matrix.x = x;
		matrix.y = y;
	}
	
	public void setMatrix(Position<Integer, Integer> matrix) {
		this.matrix = matrix;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = (radius > 0.0 ? radius : 0.0);
	}
	
	@Override
	public String toString() {
		return String.format("\n%-17s%9s%12s%10s%10s\n%-17s%9.4f%12.4f%10.2f%7d,%2d\n",
			"Name", "Longitude", "Latitude", "Radius", "Matrix",
			this.name, this.longitude.getValue(), this.latitude.getValue(), this.radius, this.matrix.x, this.matrix.y);
	}

}
