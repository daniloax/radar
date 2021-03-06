package control;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Cell;
import model.Map;

public class RadarEngine {

	private int height;
	private int width;

	private Cell center;
	private Cell[][] cells;

	private Statistics statistics;

	private List<Account> accounts;
	private List<Map> positions;
	
	private static final String OFF_CELL = "|       |";
	private static final String ON_CELL = "|   o   |";


	/**
	 * Construtor da classe RadarEngine.
	 * 
	 * @param statistics estatisticas
	 */
	public RadarEngine(Statistics statistics) {
		this.statistics = statistics;
	}

	/**
	 * 
	 */
	public void nextGeneration() {
		
		int i = 0;
		int j = 0;
		int dimension = 1;

		if (center.getRadius() / 15 >= 1)
			dimension = 2 * dimension + 1;

		else
			dimension = 1;
		
		width = dimension;
		height = dimension;

		cells = new Cell[width][height];
		
		for (Map position: positions) {
			if (shouldOn(position.getY(), position.getX())) {
				
				if (dimension > 1) {
					i = Math.abs(position.getY() + height);
					j = Math.abs(position.getX() + width);
				}
				
				if (validPosition(i, j)) {
					cells[i][j] = position.getAccount().getCell();
					makeCellOn(i, j);
				}
			}
		}
	}

	/**
	 * Ativa a celula de posicao (i, j)
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellOn(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].setOn();
			statistics.recordOn();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Verifica se uma celula na posicao (i, j) esta ativa.
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * @return verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellOn(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			if (cells[i][j] != null)
				return cells[i][j].isOn();
			else
				return false;
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas ativas no ambiente. 
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfOnCells() {
		int onCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellOn(i,j)) {
					onCells++;
				}
			}
		}
		return onCells;
	}

	/** verifica se uma celula deve ser mantida viva */
	private boolean shouldKeepOn(int i, int j) {
		return (cells[i][j].isOn()) && (numberOfNeighborhoodOnCells(i, j) == 2 || numberOfNeighborhoodOnCells(i, j) == 3);
	}

	/** verifica se uma celula deve ser ativada */
	private boolean shouldOn(int i, int j) {
		return (((center.getY() - 1 <= i) && (center.getY() + 1 >= i)) && 
				((center.getX() - 1 <= j) && (center.getX() + 1 >= j)));

	}

	/**
	 * Computa o numero de celulas vizinhas ativas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	private int numberOfNeighborhoodOnCells(int i, int j) {
		int on = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isOn()) {
					on++;
				}
			}
		}
		return on;
	}
	
	private boolean isNeighborCell(int i, int j) {
		return (center.getY() - 1 <= i) && (center.getY() + 1 >= i) && ((center.getX() - 1 <= j) && (center.getX()  + 1) >= j);
	}

	public void update() {
		
		int i, j;

		for (i = height - 1; i >= 0; i--) {
			for (j = width - 1; j >= 0; j--) {
				System.out.print(isCellOn(i, j) ? ON_CELL : OFF_CELL);
			}
			System.out.println("   " + ((i - width) * 15));

		}

	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setMap(List<Map> positions) {
		this.positions = positions;
	}

	/**
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return (a >= 0) && (a < height) && (b >= 0) && (b < width);
	}

	/** Metodos de acesso as propriedades height e width */

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setCenter(Cell center) {
		this.center = center;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
}
