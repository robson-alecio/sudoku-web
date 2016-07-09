package sudoku.solver;

import java.util.HashMap;
import java.util.Map;

public class SudokuPuzzleInMemoryRepository extends SudokuPuzzleRepository {
	
	private Map<String, SudokuPuzzle> data;
	
	public SudokuPuzzleInMemoryRepository() {
		data = new HashMap<>();
	}

	@Override
	public boolean save(SudokuPuzzle puzzle) {
		SudokuBoard problem = puzzle.getProblem();
		
		if (data.containsKey(problem.getRepresentation()))
			return false;
		
		data.put(problem.getRepresentation(), puzzle);
		
		return true;
	}

	@Override
	public SudokuPuzzle find(String boardRepresentation) {
		return data.get(boardRepresentation);
	}

}
