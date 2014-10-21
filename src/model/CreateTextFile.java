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

	public void addRecords()
	{
		Account record = new Account();
		int accountNumber = 0;
		String userName;
		double longitude;
		double latitude;
		Position position;

		Scanner input = new Scanner( System.in );

		System.out.printf( "%s\n%s\n%s\n%s\n\n",
				"To terminate input, type the end-of-file indicator ",
				"when you are prompted to enter input.",
				"On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
				"On Windows type <ctrl> z then press Enter" );

		System.out.printf( "%s\n%s", 
				"Enter account number (> 0), user name, password, longitude and latitude.",
				"? " );

		while ( input.hasNext() ) {

			try {

				record.setAccount( input.nextInt() );
				record.setUser( input.next() );
				longitude = input.nextDouble();
				latitude = input.nextDouble();
				record.setLongitude(new Longitude(longitude));
				record.setLatitude(new Latitude(latitude));

				if ( record.getAccount() > 0 ) {

					output.format( "%d %s %.4f %.4f\n", record.getAccount(), 
							record.getUser(), record.getLongitude(),
							record.getLatitude() );
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