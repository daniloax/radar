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
	private final static MenuOption[] choices = { MenuOption.SIGN_IN,
		MenuOption.SIGN_UP, MenuOption.END };
	
	public UserRequest() {
		database = new CreateTextFile();
	}

	// read records from file and display all records
	private void readRecords() {
		// object to store data that will be written to file
		Account record = new Account(); 

		try // read records
		{     
			// open file to read from beginning
			input = new Scanner( new File( "radar.txt" ) );

			while ( input.hasNext() ) // input the values from the file
			{
				record.setAccount( input.nextInt() ); // read account number
				record.setUser( input.next() ); // read first name
				record.setPassword( input.nextInt() ); // read last name
				record.setLongitude( input.nextDouble() ); // read balance
				record.setLatitude( input.nextDouble() ); // read balance

				// if proper acount type, display record
				// if ( shouldDisplay( record.getBalance() ) )
				System.out.printf( "%-10d%-12s%-12d%-12.2f%10.2f\n",
						record.getAccount(), record.getUser(), record.getPassword(),
						record.getLongitude(), record.getLatitude() );
			} // end while
		} // end try
		catch ( NoSuchElementException elementException )
		{
			System.err.println( "File improperly formed." );
			input.close();
			System.exit( 1 );
		} // end catch
		catch ( IllegalStateException stateException )
		{
			System.err.println( "Error reading from file." );
			System.exit( 1 );
		} // end catch
		catch ( FileNotFoundException fileNotFoundException )
		{
			System.err.println( "File cannot be found." );
			System.exit( 1 );
		} // end catch
		finally
		{
			if ( input != null )
				input.close(); // close the Scanner and the file
		} // end finally
	} // end method readRecords

	// use record type to determine if record should be displayed
	private boolean shouldDisplay( double balance )
	{
		if ( ( userType == MenuOption.SIGN_IN )
				&& ( balance < 0 ) )
			return true;

		else if ( ( userType == MenuOption.SIGN_UP )
				&& ( balance > 0 ) )
			return true;

		return false;
	} // end method shouldDisplay

	// obtain request from user
	private MenuOption getRequest()
	{
		Scanner textIn = new Scanner( System.in );
		int request = 1;

		// display request options
		System.out.printf( "\n%s\n%s\n%s\n%s\n",
				"Enter option", " 1 - Sign in",
				" 2 - Sign up",
				" 3 - End of run" );

		try // attempt to input menu choice
		{
			do // input user request
			{
				System.out.print( "\n? " );
				request = textIn.nextInt();
			} while ( ( request < 1 ) || ( request > 3 ) );
		} // end try
		catch ( NoSuchElementException elementException )
		{
			System.err.println( "Invalid input." );
			System.exit( 1 );
		} // end catch

		return choices[ request - 1 ]; // return enum value for option
	} // end method getRequest

	public void processRequests()
	{
		// get user's request (e.g., zero, credit or debit balance)
		userType = getRequest();

		while ( userType != MenuOption.END )
		{
			switch ( userType )
			{
			case SIGN_IN:
				return;
			case SIGN_UP:
				addRecords();
				break;
			} // end switch

			// readRecords();
			userType = getRequest();
		} // end while
	} // end method processRequests

	private void addRecords() {
		database.openFile();
		database.addRecords();
		database.closeFile();
	}
} // end class CreditInquiry

/*************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/