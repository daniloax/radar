package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;
import model.Map;
import model.Position;

public class ReadTextFile {
	
	private Scanner input;

	private void openFile(String fileName) {
		try {
			input = new Scanner( new File( fileName ) );
		} catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Error opening file." );
			System.exit( 1 );
		}
	}
	
	public void readAccounts(String fileName) {

		Account record = new Account();

		System.out.printf( "%-10s%-11s%-17s%9s%12s%10s%10s\n", "ACCOUNT",
				"PASSWORD", "CELL", "LONGITUDE", "LATITUDE", "RADIUS", "MATRIX" );
		
		openFile(fileName);

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
						record.getLongitude(), record.getLatitude(), record.getCell().getRadius(), record.getCell().getMatrix().getX(), record.getCell().getMatrix().getY() );
			}
		}  catch ( NoSuchElementException elementException ) {
			System.err.println( "File improperly formed." );
			input.close();
			System.exit( 1 );
		}  catch ( IllegalStateException stateException ) {
			System.err.println( "Error reading from file." );
			System.exit( 1 );
		} finally {
			closeFile();
		}
	}

	public void readAccounts(String fileName, List<Account> accounts) {

		Account record;
		
		openFile(fileName);

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
		} finally {
			closeFile();
		}
		
	}
	
	public void readPositions(String fileName, List<Map> map) {

		Account account;
		Map record;
		Position<Integer, Integer> position;
		
		openFile(fileName);

		try {
			while ( input.hasNext() ) {
				account = new Account();
				position = new Position<Integer, Integer>();
				record = new Map();
				position.setX(input.nextInt());
				position.setY(input.nextInt());
				account.setAccount(input.nextInt());
				record.setAccount(account);
				record.setPosition(position);
				map.add(record);
			}
		}  catch ( NoSuchElementException elementException ) {
			System.err.println( "File improperly formed." );
			input.close();
			System.exit( 1 );
		}  catch ( IllegalStateException stateException ) {
			System.err.println( "Error reading from file." );
			System.exit( 1 );
		} finally {
			closeFile();
		}
		
	}

	private void closeFile() {
		if ( input != null )
			input.close();
	}
}