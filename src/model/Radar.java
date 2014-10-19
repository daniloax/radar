package model;

import view.Keypad;
import view.Screen;
import control.CellView;
import control.PositionUpdate;
import control.RadarView;
import control.Transaction;

public class Radar {

	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Screen screen;
	private Keypad keypad;
	private RadarDatabase radarDatabase;

	private static final int CELL_VIEW = 1;
	private static final int RADAR_VIEW = 2;
	private static final int POSITION_UPDATE = 3;
	private static final int EXIT = 4;

	public Radar() {

		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		radarDatabase = new RadarDatabase();

	}

	public void run() {

		while (true) {

			while (!userAuthenticated) {

				screen.displayMessageLine("\nWelcome!");
				authenticateUser();

			}

			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;
			screen.displayMessage("\nGoodbye!");

		}

	}

	private void authenticateUser() {

		screen.displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInput();
		screen.displayMessage("\nEnter your password: ");
		int password = keypad.getInput();

		userAuthenticated = radarDatabase.authenticateUser(accountNumber, password);

		if (userAuthenticated)
			currentAccountNumber = accountNumber;

		else
			screen.displayMessageLine("Invalid account number or password. Please try again.");

	}

	private void performTransactions() {

		Transaction currentTransaction = null;

		boolean userExited = false;

		while (!userExited) {

			int mainMenuSelection = displayMainMenu();

			switch (mainMenuSelection) {

				case CELL_VIEW:
				case RADAR_VIEW:
				case POSITION_UPDATE:
					currentTransaction = createTransaction(mainMenuSelection);
					currentTransaction.execute();
					break;
	
				case EXIT:
					screen.displayMessageLine("\nExiting the system...");
					userExited = true;
					break;
	
				default:
					screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
					break;

			}

		}

	}

	private int displayMainMenu() {

		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - Cell view");
		screen.displayMessageLine("2 - Radar view");
		screen.displayMessageLine("3 - Position update");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessage("Enter a choise: ");

		return keypad.getInput();

	}

	private Transaction createTransaction(int type) {

		Transaction temp = null;

		switch (type) {

		case POSITION_UPDATE:
			temp = new PositionUpdate(currentAccountNumber, screen, radarDatabase, keypad);
			break;
		case RADAR_VIEW:
			temp = new RadarView(currentAccountNumber, screen, radarDatabase);
			break;
		case CELL_VIEW:
			temp = new CellView(currentAccountNumber, screen, radarDatabase);
			break;

		}

		return temp;

	}

}
