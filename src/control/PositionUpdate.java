package control;

import model.RadarDatabase;
import view.Keypad;
import view.Screen;

public class PositionUpdate extends Transaction {

	private int option;
	private Double latitude, longitude;
	private Keypad keypad;

	private final static int CANCELED = 3;

	public PositionUpdate(int accountNumber, Screen screen, RadarDatabase radarDatabase, Keypad keypad) {
		super(accountNumber, screen, radarDatabase);
		this.keypad = keypad;
	}

	@Override
	public void execute() {

		boolean positionUpdated = false;
		RadarDatabase radarDatabase = getRadarDatabase();
		Screen screen = getScreen();

		do {

			option = displayMenuOfUpdate();

			if (option != CANCELED) {

				radarDatabase.updatePosition(getAccountNumber(), latitude, longitude);
				positionUpdated = true;

				screen.displayMessageLine("\nYour position has been updated.");

			} else {

				screen.displayMessageLine("\nCanceling transaction...");
				return;

			}

		} while (!positionUpdated);

	}

	private int displayMenuOfUpdate() {

		int userChoice = 0;
		Screen screen = getScreen();

		while (userChoice == 0) {
			screen.displayMessageLine("\nCoordinate Update Menu:");
			screen.displayMessageLine("1 - Latitude");
			screen.displayMessageLine("2 - Longitude");
			screen.displayMessageLine("3 - Cancel transaction");
			screen.displayMessage("\nChoose a coordinate update option: ");

			int input = keypad.getInput();

			switch (input) {

				case 1:
					userChoice = input;
					screen.displayMessage("\nLatitude coordinate: ");
					latitude = getLatitude();
					break;
				case 2:
					userChoice = input;
					screen.displayMessage("\nLongitude coordinate: ");
					longitude = getLongitude();
					break;
				case CANCELED:
					userChoice = CANCELED;
					break;
				default:
					screen.displayMessageLine("\nInvalid selection. Try again.");
	
			}

		}

		return userChoice;

	}
	
	private double getLatitude() {
		return keypad.getCoodinate();
	}
	
	private double getLongitude() {
		return keypad.getCoodinate();
	}

}
