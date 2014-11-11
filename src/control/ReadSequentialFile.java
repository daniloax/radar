package control;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import model.Account;

public class ReadSequentialFile {
	
	private ObjectInputStream input;

	public void openFile() {
		try {
			input = new ObjectInputStream(new FileInputStream( "radar.ser" ) );
		} catch ( IOException ioException ) {
			System.err.println( "Error opening file." );
		}
	}

	public void readRecords() {
		
		Account record;
		System.out.printf( "%-10s%-12s%-12s%-12s%10s\n", "Account",
				"Password", "User Name", "Longitude", "Latitude" );

		try { 

			while ( true ) {

				record = ( Account ) input.readObject();

				System.out.printf( "%-10d%-12s%-12d%-12.4f%10.4f\n",
						record.getAccount(), record.getPassword(), record.getCell().getName(),
						record.getPosition().x.getValue(), record.getPosition().y.getValue() );

			}

		} catch ( EOFException endOfFileException ) {
			return;
		} catch ( ClassNotFoundException classNotFoundException ) {
			System.err.println( "Unable to create object." );
		} catch ( IOException ioException ) {
			System.err.println( "Error during reading from file." );
		}
	}
	
	public void readRecords(List<Account> accounts) {
		
		Account record;
		System.out.printf( "%-10s%-12s%-12s%-12s%10s\n", "Account",
				"Password", "User Name", "Longitude", "Latitude" );

		try { 

			while ( true ) {

				record = ( Account ) input.readObject();
				accounts.add(record);

				System.out.printf( "%-10d%-12s%-12d%-12.4f%10.4f\n",
						record.getAccount(), record.getPassword(), record.getCell().getName(),
						record.getPosition().x.getValue(), record.getPosition().y.getValue() );

			}

		} catch ( EOFException endOfFileException ) {
			return;
		} catch ( ClassNotFoundException classNotFoundException ) {
			System.err.println( "Unable to create object." );
		} catch ( IOException ioException ) {
			System.err.println( "Error during reading from file." );
		}
	}

	public void closeFile() {
		try {
			if ( input != null )
				input.close();
			System.exit( 0 );
		} catch ( IOException ioException ) {
			System.err.println( "Error closing file." );
			System.exit( 1 );
		}
	}
}