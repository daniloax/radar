package control;

public class Statistics {
	private int onCells;
	private int offCells;
	
	public Statistics() {
		onCells = 0;
		offCells = 0;
	}

	public int getOnCells() {
		return onCells;
	}

	public void recordOn() {
		this.onCells++;
	}

	public int getOffCells() {
		return offCells;
	}

	public void recordOff() {
		this.offCells++;
	}
	
	public void display() {
		System.out.println("=================================");
		System.out.println("           Statistics            ");
		System.out.println("=================================");
		System.out.println("On cells: " + offCells);
		System.out.println("Off cells: " + offCells);
		System.out.println("=================================");
	}

}
