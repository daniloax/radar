package model;

public class AccountTest {
	
	public static void main(String[] args) {
		
		Account account;
		Longitude x;
		Latitude y;
		Position<Longitude, Latitude> position;
		
		account = new Account();
		
		x = new Longitude(-179.1234);
		y = new Latitude(87.3215);
		
		account.setPosition(new Position<Longitude, Latitude>(x, y));
		
		System.out.printf("\nLongitude: %.4f\nLatitude: %.4f", account.getLongitude(), account.getLatitude());
		
	}

}
