package sudoku.solver;

public abstract class SudokuPuzzleRepository {

	private static SudokuPuzzleRepository instance;

	public static void setInstance(SudokuPuzzleRepository instance) {
		SudokuPuzzleRepository.instance = instance;
	}
	
	public static SudokuPuzzleRepository getInstance() {
		if (instance == null)
			instance = new SudokuPuzzlePostgresRepository("localhost", 5432, "sudoku-web", "sudoku", "sudoku");
		
		return instance;
	}
	
	public abstract boolean save(SudokuPuzzle puzzle);
	
	public abstract SudokuPuzzle find(String boardRepresentation);

	public abstract void clear();

}
