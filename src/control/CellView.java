package control;

import model.Coordinate;
import model.RadarDatabase;
import view.Screen;


public class CellView extends Transaction {
	
	public CellView(int accountNumber, Screen screen, RadarDatabase radarDatabase) {
		super(accountNumber, screen, radarDatabase);
	}
	
	@Override
	public void execute() {
		RadarDatabase radarDatabase = getRadarDatabase();
		Screen screen = getScreen();
		
		Coordinate currentLongitude = radarDatabase.getLongitudeCoordinate(getAccountNumber());
		Coordinate currentLatitude = radarDatabase.getLatitude(getAccountNumber());
		
		
		screen.displayMessageLine("\nPosition Information:");
		screen.displayMessage(" - Current longitude: ");
		screen.displayCoordenate(currentLongitude);
		screen.displayMessage("\n - Current latitude: ");
		screen.displayCoordenate(currentLatitude);
		screen.displayMessageLine("");
	}
}
