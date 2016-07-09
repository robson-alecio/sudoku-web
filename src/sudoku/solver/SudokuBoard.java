package sudoku.solver;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

	private static final int BOARD_SIZE = 9;
	private static final String MSG_LINEAR_INCONSISTENCY = "Número %d aparece %d vezes na %s %d.";
	private static final String MSG_SECTOR_INCONSISTENCY = "Número %d aparece %d vezes no setor %s.";

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

	private void validate() {
		List<String> inconsistencies = new ArrayList<>();
		for (int number = 1; number <= BOARD_SIZE; number++) {
			inconsistencies.addAll(findRepeatedOnLines(number));
			inconsistencies.addAll(findRepeatedOnColumns(number));
			inconsistencies.addAll(findRepeatedOnSectors(number));
		}

		if (!inconsistencies.isEmpty())
			throw new InvalidInputBoardException(inconsistencies);
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

}
