package model;

// Fig. 17.10: ReadTextFile.java
// This program reads a text file and displays each record.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile
{
	private Scanner input;

	public void openFile() {
		try {
			input = new Scanner( new File( "clients" ) );
		} catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Error opening file." );
			System.exit( 1 );
		}
	}

	public void readRecords() {
		
		Account record = new Account();
		int accountNumber = 0;
		String userName;
		double longitude;
		double latitude;
		Position position;

		System.out.printf( "%-10s%-12s%-12s%10s\n", "Account",
				"User Name", "Longitude", "Latitude" );

		try {
			while ( input.hasNext() ) {
				record.setAccount( input.nextInt() );
				record.setUser( input.next() );
				longitude = input.nextDouble();
				latitude = input.nextDouble();
				position = new Position<Longitude, Latitude>(new Longitude(longitude), new Latitude(latitude));
				record.setPosition(position);

				System.out.printf( "%-10d%-12s%-12.4f%10.4f\n",
						record.getAccount(), record.getUser(),
						record.getLongitude().getValue(), record.getLatitude().getValue() );
			}
		}  catch ( NoSuchElementException elementException ) {
			System.err.println( "File improperly formed." );
			input.close();
			System.exit( 1 );
		}  catch ( IllegalStateException stateException ) {
			System.err.println( "Error reading from file." );
			System.exit( 1 );
		} 
	}

	// close file and terminate application
	public void closeFile()
	{
		if ( input != null )
			input.close(); // close file
	} // end method closeFile
} // end class ReadTextFile

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