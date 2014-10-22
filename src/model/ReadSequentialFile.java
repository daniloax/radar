package model;

// Fig. 17.19: ReadSequentialFile.java
// Reading a file of objects sequentially with ObjectInputStream
// and displaying each record.
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadSequentialFile
{
	private ObjectInputStream input;

	// enable user to select file to open
	public void openFile()
	{
		try // open file
		{
			input = new ObjectInputStream(
					new FileInputStream( "radar.ser" ) );
		} // end try
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		} // end catch
	} // end method openFile

	// read record from file
	public void readRecords()
	{
		Account record;
		System.out.printf( "%-10s%-12s%-12s%10s\n", "Account",
				"User Name", "Longitude", "Latitude" );

		try { 

			while ( true ) {

				record = ( Account ) input.readObject();

				System.out.printf( "%-10d%-12s%-12.4f%10.4f\n",
						record.getAccount(), record.getUser(),
						record.getLongitude(), record.getLatitude() );

			}

		}
		catch ( EOFException endOfFileException )
		{
			return;
		}
		catch ( ClassNotFoundException classNotFoundException )
		{
			System.err.println( "Unable to create object." );
		}
		catch ( IOException ioException )
		{
			System.err.println( "Error during reading from file." );
		}
	}

	public void closeFile()
	{
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