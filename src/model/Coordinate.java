package model;

public abstract class Coordinate {

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
