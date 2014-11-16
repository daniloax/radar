package model;

import java.io.Serializable;

public class Cell implements Serializable {
	
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
		return String.format("\n%s\n\n%-25s%10s%10s\n%-25s%10.4f%10.4f\n",
			"Cell View", "Name", "Longitude", "Latitude", name, longitude.getValue(), latitude.getValue());
	}

}
