package sudoku.solver;

public class SudokuPuzzlePostgresRepositoryTest extends SudokuPuzzleRepositoryTest {

	@Override
	protected SudokuPuzzleRepository getRepository() {
		return new SudokuPuzzlePostgresRepository("localhost", 5432, "sudoku-web-test", "sudoku", "sudoku");
	}
	
}
