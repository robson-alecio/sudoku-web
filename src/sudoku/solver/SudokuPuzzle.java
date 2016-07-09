package sudoku.solver;

public class SudokuPuzzle {
	
	private SudokuBoard problem;
	private SudokuBoard solution;
	
	SudokuPuzzle(SudokuBoard problem, SudokuBoard solution) {
		super();
		this.problem = problem;
		this.solution = solution;
	}
	
	public SudokuBoard getProblem() {
		return problem;
	}
	
	public SudokuBoard getSolution() {
		return solution;
	}
	
	@Override
	public String toString() {
		return String.format("%s -> %s", problem, solution);
	}

}
