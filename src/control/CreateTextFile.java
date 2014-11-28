/*
 * CreateTextFile
 * ---------------------------------
 *  version: 0.0.0
 *  date: Nov 16, 2014
 *  author: ska
 *  list of changes: (none) 
 */

package control;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Account;

/**
 * Gravando dados em um arquivo de texto sequencial com a classe Formatter
 * 
 * 
 * @author ska
 */
public class CreateTextFile {
	
	private Formatter output; // objeto usado para gerar saída de texto no arquivo

	/**
	 * Permite ao usuário abrir o arquivo
	 * 
	 */
	private void openFile(String fileName) {
		try {
			output = new Formatter( fileName ); // abre arquivo
		} catch ( SecurityException securityException ) {
			System.err.println( "You do not have write access to this file." );
			System.exit( 1 );
		}  catch ( FileNotFoundException fileNotFoundException ) {
			System.err.println( "Error opening or creating file." );
			System.exit( 1 );
		}
	}

	/**
	 * Adiciona registros ao arquivo
	 * 
	 */
	public void addAccount(String fileName) {

		// objeto a ser gravado no arquivo
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
		
		openFile(fileName);

		try { // gera saída dos valores para o arquivo
			
			while ( input.hasNext() ) {
	
				record.setAccount( input.nextInt() ); // lê o número da conta
				record.getCell().setName( input.next() ); // lê o nome da célula
				record.setLongitude( input.nextDouble() ); // lê a longitude
				record.setLatitude( input.nextDouble() ); // lê a latitude
				record.getCell().setRadius( input.nextDouble() ); // lê o raio
	
				if ( record.getAccount() > 0 ) {
	
					// grava um novo registro
					output.format( "%d %s %.4f %.4f %.2f\n", record.getAccount(), 
							record.getCell().getName(), record.getLongitude(),
							record.getLatitude(), record.getCell().getRadius() );
				} else {
					System.out.println("Account number must be greater than 0." );
				}
				
				System.out.printf( "%s %s\n%s", "Enter account number (>0),",
						"user name, longitude and latitude.", "? " );
			}
	
		} catch ( FormatterClosedException formatterClosedException ) {
			System.err.println( "Error writing to file." );
			return;
		}  catch ( NoSuchElementException elementException ) {
			System.err.println( "Invalid input. Please try again." );
			input.nextLine();
		} finally {
			closeFile();
		}

			
	}

	/**
	 * Fecha o arquivo
	 * 
	 */
	private void closeFile() {
		if ( output != null )
			output.close();
	}
}