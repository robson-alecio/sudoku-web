package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SudokuPuzzleTest {

	@Test
	public void printHtml() {
		SudokuBoard problem = new SudokuBoard(
			"100008009|" +
			"002000008|" +
			"080549000|" + 
			"040200900|" +
			"309000201|" +
			"001005040|" +
			"000912030|" +
			"700000100|" +
			"200700006");
		
		SudokuBoard solution = new SudokuBoard(
			"134628579|" +
			"952137468|" +
			"687549312|" +
			"546271983|" +
			"379486251|" +
			"821395647|" +
			"468912735|" +
			"795863124|" +
			"213754896");
		
		SudokuPuzzle puzzle = new SudokuPuzzle(problem, solution);
		
		assertThat(puzzle.printHtml()).isEqualTo("");
	}
}
