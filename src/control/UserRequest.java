package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;
import model.MenuOption;

public class UserRequest {
	
	private CreateTextFile database;
	private MenuOption userType;
	private Scanner input;
	private final static MenuOption[] choices = { MenuOption.SIGN_IN, MenuOption.SIGN_UP, MenuOption.END };
	
	public UserRequest() {
		database = new CreateTextFile();
	}

	private void readRecords() {

		Account record = new Account(); 

		try {

			input = new Scanner( new File( "radar.txt" ) );

			while ( input.hasNext() ) {
				record.setAccount( input.nextInt() );
				record.setUser( input.next() );
				record.setPassword( input.nextInt() );
				record.setLongitude( input.nextDouble() );
				record.setLatitude( input.nextDouble() );

				System.out.printf( "%-10d%-12s%-12d%-12.2f%10.2f\n",
						record.getAccount(), record.getUser(), record.getPassword(),
						record.getLongitude(), record.getLatitude() );
			}
		
		} catch ( NoSuchElementException elementException ) {
			
			System.err.println( "File improperly formed." );
			input.close();
			System.exit( 1 );
		
		} catch ( IllegalStateException stateException ) {
			
			System.err.println( "Error reading from file." );
			System.exit( 1 );
		
		} catch ( FileNotFoundException fileNotFoundException ) {
			
			System.err.println( "File cannot be found." );
			System.exit( 1 );
		
		} finally {
			
			if ( input != null ) input.close();
		
		}
	
	}

	private MenuOption getRequest() {
		
		Scanner textIn = new Scanner( System.in );
		int request = 1;

		System.out.printf( "\n%s\n%s\n%s\n%s\n",
				"Enter option", " 1 - Sign in",
				" 2 - Sign up",
				" 3 - End of run" );

		try {
			
			do {
				
				System.out.print( "\n? " );
				request = textIn.nextInt();
			
			} while ( ( request < 1 ) || ( request > 3 ) );
		
		} catch ( NoSuchElementException elementException ) {
			
			System.err.println( "Invalid input." );
			System.exit( 1 );
		
		}

		return choices[ request - 1 ];
	
	}

	public void processRequests() {

		userType = getRequest();

		switch ( userType ) {
		
			case SIGN_IN:
				return;
			
			case SIGN_UP:
				addRecords();
				return;
				
			case END:
				System.exit( 1 );
		
		}
	
	}

	private void addRecords() {
		
		database.openFile();
		database.addRecords();
		database.closeFile();
	
	}

}