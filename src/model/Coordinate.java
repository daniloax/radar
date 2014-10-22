package model;

import java.io.Serializable;

public abstract class Coordinate implements Serializable {

	private double value;
	
	public Coordinate() {
		
	}
	
	public Coordinate(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
}
