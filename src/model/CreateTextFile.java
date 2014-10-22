package model;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile
{
	private Formatter output;

	public void openFile() {
		try {
			output = new Formatter( "radar.txt" );
		} catch ( SecurityException securityException ) {
			System.err.println( "You do not have write access to this file." );
			System.exit( 1 );
		}  catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Error opening or creating file." );
			System.exit( 1 );
		}
	}

	public void addRecords() {

		Account record = new Account();

		Scanner input = new Scanner( System.in );

		System.out.printf( "%s\n%s\n%s\n%s\n\n",
				"To terminate input, type the end-of-file indicator ",
				"when you are prompted to enter input.",
				"On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
				"On Windows type <ctrl> z then press Enter" );

		System.out.printf( "%s\n%s", 
				"Enter account number (> 0), user name, longitude and latitude.",
				"? " );

		while ( input.hasNext() ) {

			try {

				record.setAccount( input.nextInt() );
				record.setUser( input.next() );
				record.setLongitude(input.nextDouble());
				record.setLatitude(input.nextDouble());

				if ( record.getAccount() > 0 ) {

					output.format( "%d %s %.4f %.4f\n", record.getAccount(), 
							record.getUser(), record.getPosition().x.getValue(),
							record.getPosition().y.getValue() );
				} else {
					System.out.println("Account number must be greater than 0." );
				}

			} catch ( FormatterClosedException formatterClosedException ) {
				System.err.println( "Error writing to file." );
				return;
			}  catch ( NoSuchElementException elementException ) {
				System.err.println( "Invalid input. Please try again." );
				input.nextLine();
			}

			System.out.printf( "%s %s\n%s", "Enter account number (>0),",
					"user name, longitude and latitude.", "? " );
		}
	}

	public void closeFile() {
		if ( output != null )
			output.close();
	}
}