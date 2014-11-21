package control;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import model.Cell;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class RadarEngine {
	
	private int height;
	private int width;
	
	private Cell cell;
	private Cell[][] cells;
	private Statistics statistics;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimensao horizontal do ambiente
	 */
	public RadarEngine(Statistics statistics) {
		this.statistics = statistics;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
	public void nextGeneration() {
		List<Cell> mustOn = new ArrayList<Cell>();
		List<Cell> mustOff = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (shouldOn(i, j)) {
					mustOn.add(cells[i][j]);
				} 
				else if ((!shouldKeepOn(i, j)) && cells[i][j].isOn()) {
					mustOn.add(cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustOn) {
			cell.setOn();
			statistics.recordOn();
		}
		
		for (Cell cell : mustOff) {
			cell.setOff();
			statistics.recordOff();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
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
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
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
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
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

	/* verifica se uma celula deve ser mantida viva */
	private boolean shouldKeepOn(int i, int j) {
		return (cells[i][j].isOn()) && (numberOfNeighborhoodOnCells(i, j) == 2 || numberOfNeighborhoodOnCells(i, j) == 3);
	}

	/* verifica se uma celula deve (re)nascer */
	public boolean shouldOn(int i, int j) {
		if (cells[i][j] != null) {
			if (cells[i][j] != cell) {
				double xA = cell.getX();
				double yA = cell.getY();
				double raioA = cell.getRadius();
				
				double xB = cells[i][j].getX();
				double yB = cells[i][j].getY();
				
				boolean isIn = (Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2) - Math.pow(raioA, 2)) <= 0;
				return isIn && !cells[i][j].isOn();
			}
			return !cells[i][j].isOn();
		
		} else
			return false;

	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
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

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {		
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	
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
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
}