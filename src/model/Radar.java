/*
 * Radar
 * ---------------------------------
 *  version: 0.0.0
 *  date: Nov 16, 2014
 *  author: ska
 *  list of changes: (none) 
 */

package model;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import view.Keypad;
import view.Screen;
import control.CellView;
import control.PositionUpdate;
import control.RadarController;
import control.RadarEngine;
import control.RadarView;
import control.RadiusUpdate;
import control.Statistics;
import control.Transaction;

/**
 * Representa um Radar
 * 
 * 
 * @author ska
 */
public class Radar {

	private boolean userAuthenticated;
	private int currentAccountNumber;
	
	private Screen screen;
	private Keypad keypad;
	private RadarDatabase radarDatabase;
	
	private RadarController radarController;
	private RadarEngine radarEngine;
	
	private Statistics statistics;
	
	private final static MenuOption[] menuOption = { MenuOption.SIGN_IN, MenuOption.SIGN_UP, MenuOption.END };
	private final static MainMenu[] mainMenuOption = { MainMenu.CELL_VIEW, MainMenu.RADAR_VIEW, MainMenu.POSITION_UPDATE, MainMenu.RADIUS_UPDATE, MainMenu.EXIT };
	
	/**	
	 * construtor sem argumentos de Radar inicializa as variáveis de instância
	 *
	 */
	public Radar() {
		
		userAuthenticated = false;
		currentAccountNumber = 0;
		
		screen = new Screen();
		keypad = new Keypad();
		
		radarController = new RadarController();
		radarDatabase = new RadarDatabase();
		
		statistics = new Statistics();
		
		radarEngine = new RadarEngine(statistics);
		
	}
	
	/**	
	 * inicia o radar
	 *
	 */
	public void run() {

		while (true) {

			screen.displayMessageLine("\nWelcome!");
			processRequests();
			
			while (!userAuthenticated)
				authenticateUser();

			screen.displayMessageLine(String.format("\nOlá, %s!",
					radarDatabase.getUser(currentAccountNumber)));

			performTransactions();
			userAuthenticated = false;
			currentAccountNumber = 0;

		}

	}

	/**	
	 * tenta autenticar o usuário contra o banco de dados
	 *
	 */
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
	
	/**	
	 * exibe o menu de opções e retorna uma seleção de entrada
	 *
	 */
	private MenuOption displayMenuOption() {
			
		int userType = 1;
		
		try {
		
			do {
		
				screen.displayMessageLine("\nMenu option");
				screen.displayMessageLine("[1] Sign in");
				screen.displayMessageLine("[2] Sign up");
				screen.displayMessageLine("[3] End of run");
				screen.displayMessage( "\n? " );
				
				userType = keypad.getInput();
				
			} while(userType < 1 || userType > 3);
			
		} catch (InputMismatchException inputMismatchException) {
			
			System.err.println("Invalid input.");
			System.exit( 1 );
		
		} catch (NoSuchElementException elementException) {
			
			System.err.println( "Invalid input." );
			System.exit( 1 );
			
		}
		
		return menuOption[ userType - 1 ];
		
	}
	
	/**	
	 * executa o menu de opções e processa requisições
	 *
	 */
	private void processRequests() {
		
		MenuOption menuOption = displayMenuOption();

		switch ( menuOption ) {
		
			case SIGN_IN:
				radarDatabase.readAccounts();
				radarDatabase.readPositions();
				break;
			
			case SIGN_UP:
				radarDatabase.addAccounts();
				radarDatabase.readAccounts();
				break;
				
			case END:
				screen.displayMessageLine("\nExiting the system...");
				System.exit( 1 );
			
			default:
				screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
				break;
		
		}

	}	
	
	/**	
	 * exibe o menu principal e retorna uma seleção de entrada
	 *
	 */
	private MainMenu displayMainMenu() {

		int userType = 1;
		
		try {
			
			do {
		
				screen.displayMessageLine("\nMain menu:");
				screen.displayMessageLine("[1] Cell view");
				screen.displayMessageLine("[2] Radar view");
				screen.displayMessageLine("[3] Position update");
				screen.displayMessageLine("[4] Radius update");
				screen.displayMessageLine("[5] Exit\n");
				screen.displayMessage("Enter a choise: ");
				
				userType = keypad.getInput();
				
			} while(userType < 1 || userType > 5);
			
		} catch (InputMismatchException inputMismatchException) {
			
			System.err.println("");
		
		} catch ( NoSuchElementException elementException ) {
			
			System.err.println( "Invalid input." );
			System.exit( 1 );
		
		}

		return mainMenuOption[userType - 1];

	}
	
	/**	
	 * executa o menu principal e realiza transações
	 *
	 */
	private void performTransactions() {

		boolean userExited = false;
		
		MainMenu mainMenuSelection;
		Transaction currentTransaction = null;

		while (!userExited) {

			mainMenuSelection = displayMainMenu();

			switch (mainMenuSelection) {

				case CELL_VIEW:
				case RADAR_VIEW:
				case POSITION_UPDATE:
				case RADIUS_UPDATE:
					currentTransaction = createTransaction(mainMenuSelection);
					currentTransaction.execute();
					break;
	
				case EXIT:
					screen.displayMessageLine("\nGoodbye!");
					userExited = true;
					break;
	
				default:
					screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
					break;

			}

		}

	}

	/**	
	 * retorna o objeto da subclasse de Transaction especificada
	 *
	 */
	private Transaction createTransaction(MainMenu type) {

		Transaction temp = null;

		switch (type) {
		
			case CELL_VIEW:
				temp = new CellView(currentAccountNumber, screen, radarDatabase);
				break;
				
			case RADAR_VIEW:
				temp = new RadarView(currentAccountNumber, screen, radarDatabase, keypad, radarController, radarEngine, statistics);
				break;
	
			case POSITION_UPDATE:
				temp = new PositionUpdate(currentAccountNumber, screen, radarDatabase, keypad);
				break;
				
			case RADIUS_UPDATE:
				temp = new RadiusUpdate(currentAccountNumber, screen, radarDatabase, keypad);
				break;
			
			default:
				break;
		
		}

		return temp;

	}

}
