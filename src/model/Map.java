package model;

public class Map {
	
	private Account account;
	private Position<Integer, Integer> position;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Position<Integer, Integer> getPosition() {
		return position;
	}
	public void setPosition(Position<Integer, Integer> position) {
		this.position = position;
	}
	public int getX() {
		return position.getX();
	}
	public void setX(int x) {
		this.position.setX(x);
	}
	public int getY() {
		return position.getY();
	}
	public void setY(int y) {
		this.position.setY(y);
	}
}
