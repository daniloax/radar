package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;

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

		System.out.printf( "%-10s%-11s%-17s%9s%12s%10s%10s\n", "ACCOUNT",
				"PASSWORD", "CELL", "LONGITUDE", "LATITUDE", "RADIUS", "SQUARE" );

		try {
			while ( input.hasNext() ) {
				record.setAccount( input.nextInt() );
				record.setPassword( input.nextInt() );
				record.getCell().setName( input.next() );
				record.setLongitude(input.nextDouble());
				record.setLatitude(input.nextDouble());
				record.getCell().setRadius(input.nextDouble());
				record.getCell().setMatrix();

				System.out.printf( "%-10d%-11s%-17s%9.4f%12.4f%10.2f%7d,%2d\n",
						record.getAccount(), record.getPassword(), record.getCell().getName(),
						record.getLongitude(), record.getLatitude(), record.getCell().getRadius(), record.getCell().getMatrix().x, record.getCell().getMatrix().y );
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

	public void readRecords(List<Account> accounts) {

		Account record;

		try {
			while ( input.hasNext() ) {
				record = new Account();
				record.setAccount( input.nextInt() );
				record.setPassword( input.nextInt() );
				record.getCell().setName( input.next() );
				record.setLongitude( input.nextDouble() );
				record.setLatitude( input.nextDouble() );
				record.getCell().setRadius( input.nextDouble() );
				record.getCell().setMatrix();
				accounts.add(record);				
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