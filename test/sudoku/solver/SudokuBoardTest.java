package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SudokuBoardTest {
	
	@Test
	public void boardRepresentationEqualsToString() {
		SudokuBoard problem = new SudokuBoard("080409653|642800070|000000800|007005042|000701000|850600100|006000000|010004736|273508010");
		assertThat(problem.toString()).isEqualTo("080409653|642800070|000000800|007005042|000701000|850600100|006000000|010004736|273508010");
		
		SudokuBoard solution = new SudokuBoard("781429653|642853971|935176824|167985342|329741568|854632197|496317285|518294736|273568419");
		assertThat(solution.toString()).isEqualTo("781429653|642853971|935176824|167985342|329741568|854632197|496317285|518294736|273568419");
	}

}
