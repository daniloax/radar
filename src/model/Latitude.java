package model;

public class Latitude extends Coordinate {
	
	public Latitude() {
		super();
	}
	
	public Latitude(double value) {
		super((value >= -90.0 && value <= 90.0) ? value : 0.0);
	}
	
	@Override
	public void setValue(double value) {
		super.setValue((value >= -90.0 && value <= 90.0) ? value : 0.0);
	}

}
