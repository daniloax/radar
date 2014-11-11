package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;
import model.Latitude;
import model.Longitude;
import model.Position;

public class CreateSequentialFile
{
	private ObjectOutputStream output;

	public void openFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream( "radar.ser" ) );
		} catch ( IOException ioException ) {
			System.err.println( "Error opening file." );
		}
	}

	public void addRecords() {
		Account record;
		int accountNumber = 0;
		int password = 0;
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
				"Enter account number (> 0), password, user name, longitude and latitude.",
				"? " );

		while ( input.hasNext() ) {
			
			try {
				
				accountNumber = input.nextInt();
				password = input.nextInt();
				userName = input.next();
				longitude = input.nextDouble();
				latitude = input.nextDouble();
				position = new Position(new Longitude(longitude), new Latitude(latitude));
				
				if ( accountNumber > 0 ) {
					
					record = new Account( accountNumber, password, userName, position );
					output.writeObject( record );
				
				} else {
					
					System.out.println(
							"Account number must be greater than 0." );
				
				}
			
			} catch ( IOException ioException ) {
				
				System.err.println( "Error writing to file." );
				return;
			
			} catch ( NoSuchElementException elementException ) {
				
				System.err.println( "Invalid input." );
				input.nextLine();
			
			}

			System.out.printf( "%s %s\n%s", "Enter account number (> 0),",
					"password, user name, longitude and latitude.", "? " );
		
		}
	
	}

	public void closeFile() {
		try  {
			if ( output != null )
				output.close();
		} catch ( IOException ioException ) {
			System.err.println( "Error closing file." );
			System.exit( 1 );
		}
	}
}
