package model;

public class Latitude {

	private double value;
	
	public Latitude() {

	}
	
	public Latitude(double value) {
		this.value = (value >= -90.0 && value <= 90.0) ? value : 0.0;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = (value >= -90.0 && value <= 90.0) ? value : 0.0;
	}

}
