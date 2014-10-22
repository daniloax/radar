package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile {
	
	private Scanner input;

	public void openFile() {
		try {
			input = new Scanner( new File( "radar.txt" ) );
		} catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Error opening file." );
			System.exit( 1 );
		}
	}

	public void readRecords() {

		Account record = new Account();

		System.out.printf( "%-10s%-12s%-12s%10s\n", "Account",
				"User Name", "Longitude", "Latitude" );

		try {
			while ( input.hasNext() ) {
				record.setAccount( input.nextInt() );
				record.setUser( input.next() );
				record.setLongitude(input.nextDouble());
				record.setLatitude(input.nextDouble());

				System.out.printf( "%-10d%-12s%-12.4f%10.4f\n",
						record.getAccount(), record.getUser(),
						record.getLongitude(), record.getLatitude() );
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

	public void closeFile() {
		if ( input != null )
			input.close();
	}
}