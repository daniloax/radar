
package model;

import java.io.Serializable;

public class Position<X, Y> implements Serializable {
	
	public X x;
	public Y y;
	
	public Position(X x, Y y) {
		this.x = x;
		this.y = y;
	}

}
