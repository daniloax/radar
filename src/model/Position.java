
package model;

import java.io.Serializable;

public class Position<X, Y> implements Serializable {
	
	private static final long serialVersionUID = -4029913290429621687L;
	private X x;
	private Y y;
	
	public Position() {

	}
	
	public Position(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	public X getX() {
		return x;
	}
	
	public void setX(X x) {
		this.x = x;
	}
	
	public Y getY() {
		return y;
	}
	
	public void setY(Y y) {
		this.y = y;
	}

}
