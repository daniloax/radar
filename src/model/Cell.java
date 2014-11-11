package model;

import java.io.Serializable;

public class Cell implements Serializable {
	
	private String name;
	
	public Cell() {

	}
	
	public Cell(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
