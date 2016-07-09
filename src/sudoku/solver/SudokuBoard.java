package sudoku.solver;

public class SudokuBoard {
	
	private int[][] cells;
	
	public SudokuBoard(String boardRepresentation) {
		cells = new int[9][9];
		
		String[] lines = boardRepresentation.split("\\|");
		int lineNumber = 0;
		for (String line : lines) {
			String[] columns = line.split("(?<=\\G.)");
			for (int columnNumber = 0; columnNumber < columns.length; columnNumber++)
				cells[lineNumber][columnNumber] = Integer.valueOf(columns[columnNumber]);
			
			lineNumber++;
		}
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

}
