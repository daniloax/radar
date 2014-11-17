/*
 * Radar
 * ---------------------------------
 *  version: 0.0.0
 *  date: Nov 16, 2014
 *  author: ska
 *  list of changes: (none) 
 */

package model;

import view.Keypad;
import view.Screen;
import control.CellView;
import control.PositionUpdate;
import control.RadarView;
import control.Transaction;

/**
 * Representa um Radar
 * 
 * 
 * @author ska
 */
public class Radar {

	private boolean userAuthenticated; // se usuário foi autenticado
	private int currentAccountNumber; // número atual da conta do usuário
	
	private Screen screen; // tela do radar
	private Keypad keypad; // teclado do radar
	private RadarDatabase radarDatabase; // banco de dados com informações sobre as contas
	
	private final static MenuOption[] menuOption = { MenuOption.SIGN_IN, MenuOption.SIGN_UP, MenuOption.END };
	private final static MainMenu[] mainMenuOption = { MainMenu.CELL_VIEW, MainMenu.RADAR_VIEW, MainMenu.POSITION_UPDATE, MainMenu.EXIT };
	
	/**	
	 * construtor sem argumentos de Radar inicializa as variáveis de instância
	 *
	 */
	public Radar() {

		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		radarDatabase = new RadarDatabase();

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

			screen.displayMessageLine(String.format("\nHello, %s!",
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
	 * exibe o menu inicial e retorna uma seleção de entrada
	 *
	 */
	private MenuOption displayStartMenu() {
		
		screen.displayMessageLine("\nMenu option");
		screen.displayMessageLine(" 1 - Sign in");
		screen.displayMessageLine(" 2 - Sign up");
		screen.displayMessageLine(" 3 - End of run");
		screen.displayMessage( "\n? " );
		
		return menuOption[ keypad.getInput() - 1 ];
		
	}
	
	/**	
	 * executa o menu inicial e processa requisições
	 *
	 */
	private void processRequests() {
		
		MenuOption menuOption = displayStartMenu();

		switch ( menuOption ) {
		
			case SIGN_IN:
				radarDatabase.readRecords();
				break;
			
			case SIGN_UP:
				radarDatabase.addRecords();
				radarDatabase.readRecords();
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

		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - Cell view");
		screen.displayMessageLine("2 - Radar view");
		screen.displayMessageLine("3 - Position update");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessage("Enter a choise: ");

		return mainMenuOption[keypad.getInput() - 1];

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
				temp = new RadarView(currentAccountNumber, screen, radarDatabase);
				break;
	
			case POSITION_UPDATE:
				temp = new PositionUpdate(currentAccountNumber, screen, radarDatabase, keypad);
				break;
		
		}

		return temp;

	}

}
