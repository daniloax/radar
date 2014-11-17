package model;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private double radius;
	private Longitude longitude;
	private Latitude latitude;	
	private Position<Longitude, Latitude> position;
	private String name;
	
	public Cell() {
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
	}
	
	public Cell(String name) {
		this.name = name;
		longitude = new Longitude();
		latitude = new Latitude();
		position = new Position<Longitude, Latitude>(longitude, latitude);
	}
	
	public Cell(String name, Longitude longitude, Latitude latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		position = new Position<Longitude, Latitude>(longitude, latitude);
	}
	
	public Cell(String name, Position<Longitude, Latitude> position) {
		this.name = name;
		this.longitude.setValue(position.x.getValue());
		this.latitude.setValue(position.y.getValue());
		this.position = position;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = (radius > 0.0 ? radius : 0.0);
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
	
	@Override
	public String toString() {
		return String.format("\n%s\n\n%-7s%9s%12s%10s\n%-7s%9.4f%12.4f%10.2f\n",
			"Cell View", "Name", "Longitude", "Latitude", "Radius",
			this.name, this.longitude.getValue(), this.latitude.getValue(), this.radius);
	}

}
