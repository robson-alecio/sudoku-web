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
		
		SudokuPuzzle puzzleSaved = SudokuPuzzleRepository.getInstance().find(boardRepresentation);
		if (puzzleSaved != null)
			return puzzleSaved;
		
		SudokuBoard problemBoard = new SudokuBoard(boardRepresentation);
		SudokuBoard generatedSolution = generateSolution(problemBoard);
		
		SudokuPuzzle puzzle = new SudokuPuzzle(problemBoard, generatedSolution);
		
		SudokuPuzzleRepository.getInstance().save(puzzle);
		
		return puzzle;
	}

	protected SudokuBoard generateSolution(SudokuBoard problemBoard) {
		return null;
	}

}
