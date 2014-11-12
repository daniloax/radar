package model;

public class Longitude {

	private double value;
	
	public Longitude() {

	}
	
	public Longitude(double value) {
		this.value = (value >= -180.0 && value <= 180.0) ? value : 0.0;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = (value >= -180.0 && value <= 180.0) ? value : 0.0;
	}

}
