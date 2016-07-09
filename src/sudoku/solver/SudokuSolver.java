package sudoku.solver;

public class SudokuSolver {

	private static SudokuSolver instance;

	public static void setInstance(SudokuSolver instance) {
		SudokuSolver.instance = instance;
	}

	public static SudokuSolver getInstance() {
		if (instance == null)
			instance = new SudokuSolver();
		
		return instance;
	}

	public SudokuPuzzle solve(String boardRepresentation) {
		return null;
	}

}
