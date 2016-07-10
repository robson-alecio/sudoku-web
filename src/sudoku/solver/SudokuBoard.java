package sudoku.solver;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

	public static final int BOARD_SIZE = 9;
	private static final int EMPTY_VALUE = 0;
	private static final int SIMPLE_MARK_VALUE = -1;
	private static final int INFERED_MARK_VALUE = -2;
	
	private static final String MSG_LINEAR_INCONSISTENCY = "Número %d aparece %d vezes na %s %d.";
	private static final String MSG_SECTOR_INCONSISTENCY = "Número %d aparece %d vezes no setor %s.";
	private static final Object PRINT_DIVISION_ROW = "+-----------+-----------+-----------+\n";

	private int[][] cells;

	SudokuBoard(String boardRepresentation) {
		cells = new int[BOARD_SIZE][BOARD_SIZE];

		String[] lines = boardRepresentation.split("\\|");
		int lineNumber = 0;
		for (String line : lines) {
			String[] columns = line.split("(?<=\\G.)");
			for (int columnNumber = 0; columnNumber < BOARD_SIZE; columnNumber++)
				cells[lineNumber][columnNumber] = Integer.valueOf(columns[columnNumber]);

			lineNumber++;
		}

		validate();
	}

	private SudokuBoard(int[][] cells) {
		this.cells = cells;
	}

	private void validate() {
		List<String> inconsistencies = findInconsistencies();

		if (!inconsistencies.isEmpty())
			throw new InvalidInputBoardException(inconsistencies);
	}

	private List<String> findInconsistencies() {
		List<String> inconsistencies = new ArrayList<>();
		for (int number = 1; number <= BOARD_SIZE; number++) {
			inconsistencies.addAll(findRepeatedOnLines(number));
			inconsistencies.addAll(findRepeatedOnColumns(number));
			inconsistencies.addAll(findRepeatedOnSectors(number));
		}
		return inconsistencies;
	}

	private List<String> findRepeatedOnLines(int number) {
		List<String> inconsistencies = new ArrayList<>();

		for (int line = 0; line < BOARD_SIZE; line++) {
			int count = 0;
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cells[line][column] == number)
					count++;
			}

			if (count > 1)
				inconsistencies.add(String.format(MSG_LINEAR_INCONSISTENCY, number, count, "linha", line + 1));
		}

		return inconsistencies;
	}

	private List<String> findRepeatedOnColumns(int number) {
		List<String> inconsistencies = new ArrayList<>();

		for (int column = 0; column < BOARD_SIZE; column++) {
			int count = 0;
			for (int line = 0; line < BOARD_SIZE; line++) {
				if (cells[line][column] == number)
					count++;
			}

			if (count > 1)
				inconsistencies.add(String.format(MSG_LINEAR_INCONSISTENCY, number, count, "coluna", column + 1));
		}

		return inconsistencies;
	}

	private List<String> findRepeatedOnSectors(int number) {
		List<String> inconsistencies = new ArrayList<>();

		for (BoardSector sector : BoardSector.values()) {
			int count = 0;
			for (int line = sector.getStart().line; line <= sector.getEnd().line; line++) {
				for (int column = sector.getStart().column; column <= sector.getEnd().column; column++) {
					if (cells[line][column] == number)
						count++;
				}
			}
			
			if (count > 1)
				inconsistencies.add(String.format(MSG_SECTOR_INCONSISTENCY, number, count, sector.getDescription()));
		}

		return inconsistencies;
	}

	public String getRepresentation() {
		StringBuilder sb = new StringBuilder();

		for (int line = 0; line < 9; line++) {
			for (int column = 0; column < 9; column++)
				sb.append(cells[line][column]);

			if (line < 8)
				sb.append("|");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	public SudokuBoard copy() {
		return new SudokuBoard(copyCells(cells));
	}

	private int[][] copyCells(int[][] cells) {
		int[][] temp = new int[9][9];
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				temp[line][column] = cells[line][column];
			}
		}
		return temp;
	}

	public boolean isSolved() {
		return !hasEmptyCells() && findInconsistencies().isEmpty();
	}

	private boolean hasEmptyCells() {
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cells[line][column] == EMPTY_VALUE)
					return true;
			}
		}		
		return false;
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append(PRINT_DIVISION_ROW);
		
		for (int line = 0; line < BOARD_SIZE; line++) {
			sb.append("| ");
			for (int column = 0; column < BOARD_SIZE; column++) {
				int intValue = cells[line][column];
				String printValue = intValue == EMPTY_VALUE ? " " : String.valueOf(intValue);
				String format = intValue < 0 ? "%s " : " %s ";
				sb.append(String.format(format, printValue));
				
				if (column == 2 || column == 5)
					sb.append(" | ");
			}
			sb.append(" |\n");
			
			if (line == 2 || line == 5)
				sb.append(PRINT_DIVISION_ROW);
		}
		sb.append(PRINT_DIVISION_ROW);
		
		return sb.toString();
	}

	public BoardPoint eval(int number, BoardSector sector) {
		BoardPoint targetPoint = null;
		
		for (int line = sector.getStart().line; line <= sector.getEnd().line; line++) {
			for (int column = sector.getStart().column; column <= sector.getEnd().column; column++) {
				int cellValue = cells[line][column];
				if (cellValue == number)
					return null;
				
				if (cellValue == EMPTY_VALUE && targetPoint != null)
					return null;
				
				if (cellValue == EMPTY_VALUE && targetPoint == null)
					targetPoint = new BoardPoint(line, column);
			}
		}
		
		return targetPoint;
	}

	public SudokuBoard set(int number, BoardPoint point) {
		int[][] newCells = copyCells(cells);
		newCells[point.line][point.column] = number;
		return new SudokuBoard(newCells);
	}

	public SudokuBoard simpleMark(int number) {
		List<Integer> linesContaingNumber = new ArrayList<>();
		List<Integer> columnsContaingNumber = new ArrayList<>();
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cells[line][column] == number) {
					linesContaingNumber.add(line);
					columnsContaingNumber.add(column);
				}
			}
		}
		
		int[][] cellsToMark = copyCells(cells);
		
		for (int column : columnsContaingNumber) {
			for (int line = 0; line < BOARD_SIZE; line++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = SIMPLE_MARK_VALUE;
				}
			}
		}
		
		for (int line : linesContaingNumber) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = SIMPLE_MARK_VALUE;
				}
			}
		}
		
		return new SudokuBoard(cellsToMark);
	}

	public List<SudokuBoard> inferLinesAndMark(int number) {
		List<Integer> inferedLinesForNumber = new ArrayList<>();
		
		for (BoardSector sector : BoardSector.values()) {
			int line = evalLine(number, sector);
			if (line > -1) {
				inferedLinesForNumber.add(line);
				log(number, "linha", line, sector);
			}
		}


		List<SudokuBoard> list = new ArrayList<>();
		
		for (int line : inferedLinesForNumber) {
			int[][] cellsToMark = copyCells(cells);
			
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = INFERED_MARK_VALUE;
				}
			}
			
			list.add(new SudokuBoard(cellsToMark));
		}
		
		return list;
	}

	private void log(int number, String type, int value, BoardSector sector) {
		System.out.printf("Inferido %d em %s %d do setor %s.\n", number, type, value, sector);
	}

	private int evalLine(int number, BoardSector sector) {
		int targetLine = -1;
		
		for (int line = sector.getStart().line; line <= sector.getEnd().line; line++) {
			for (int column = sector.getStart().column; column <= sector.getEnd().column; column++) {
				int cellValue = cells[line][column];
				if (cellValue == number)
					return -1;
				
				boolean lineAlreadyFound = targetLine > -1;
				boolean differentLine = targetLine != line;
				if (cellValue == EMPTY_VALUE && lineAlreadyFound && differentLine)
					return -1;
				
				if (cellValue == EMPTY_VALUE && targetLine == -1)
					targetLine = line;
			}
		}
		
		return targetLine;
	}

	public List<SudokuBoard> inferColumnsAndMark(int number) {
		List<Integer> inferedColumnsForNumber = new ArrayList<>();
		
		for (BoardSector sector : BoardSector.values()) {
			int column = evalColumn(number, sector);
			if (column > -1) {
				inferedColumnsForNumber.add(column);
				log(number, "coluna", column, sector);
			}
		}


		List<SudokuBoard> list = new ArrayList<>();
		for (int column : inferedColumnsForNumber) {
			int[][] cellsToMark = copyCells(cells);
			
			for (int line = 0; line < BOARD_SIZE; line++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = INFERED_MARK_VALUE;
				}
			}
			
			list.add(new SudokuBoard(cellsToMark));
		}
		
		return list;
	}

	private int evalColumn(int number, BoardSector sector) {
		int targetColumn = -1;
		
		for (int column = sector.getStart().column; column <= sector.getEnd().column; column++) {
			for (int line = sector.getStart().line; line <= sector.getEnd().line; line++) {
				int cellValue = cells[line][column];
				if (cellValue == number)
					return -1;
				
				boolean columnAlreadyFound = targetColumn > -1;
				boolean differentColumn = targetColumn != column;
				if (cellValue == EMPTY_VALUE && columnAlreadyFound && differentColumn)
					return -1;
				
				if (cellValue == EMPTY_VALUE && targetColumn == -1)
					targetColumn = column;
			}
		}
		
		return targetColumn;
	}

	public SudokuBoard inferFullPlan(int number) {
		List<Integer> inferedLinesForNumber = new ArrayList<>();
		List<Integer> inferedColumnsForNumber = new ArrayList<>();
		
		for (BoardSector sector : BoardSector.values()) {
			int line = evalLine(number, sector);
			if (line > -1) {
				inferedLinesForNumber.add(line);
				log(number, "linha", line, sector);
			}

			int column = evalColumn(number, sector);
			if (column > -1) {
				inferedColumnsForNumber.add(column);
				log(number, "coluna", column, sector);
			}
		}

		int[][] cellsToMark = copyCells(cells);

		for (int line : inferedLinesForNumber) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = INFERED_MARK_VALUE;
				}
			}
		}

		for (int column : inferedColumnsForNumber) {
			for (int line = 0; line < BOARD_SIZE; line++) {
				if (cellsToMark[line][column] == EMPTY_VALUE) {
					cellsToMark[line][column] = INFERED_MARK_VALUE;
				}
			}
		}
		
		return new SudokuBoard(cellsToMark);
	}

}
