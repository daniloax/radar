package control;

import java.security.InvalidParameterException;

public class RadarController {

	private RadarEngine engine;
	private RadarView radar;
	private Statistics statistics;
	
	public RadarEngine getEngine() {
		return engine;
	}
	
	public void setEngine(RadarEngine engine) {
		this.engine = engine;
	}
	
	public RadarView getRadar() {
		return radar;
	}
	
	public void setRadar(RadarView radar) {
		this.radar = radar;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		radar.update();
	}
	
	public void halt() {
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellOn(int i, int j) {
		try {
			engine.makeCellOn(i, j);
			radar.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
	}
	
}
