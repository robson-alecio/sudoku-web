package sudoku.solver;

public class SudokuPuzzleInMemoryRepositoryTest extends SudokuPuzzleRepositoryTest {

	@Override
	protected SudokuPuzzleRepository getRepository() {
		return new SudokuPuzzleInMemoryRepository();
	}

}
