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
		SudokuBoard solutionProposal = problemBoard.copy();
		
		log(solutionProposal);
		
		if (solutionProposal.isSolved())
			return solutionProposal;
		
		SudokuBoard newProposal = fillOneCellLeftOnSectors(solutionProposal);
		
		log(newProposal);
		if (newProposal.isSolved())
			return newProposal;

		SudokuBoard freshNewProposal = fillUsingSimpleMarkHeuristic(newProposal);
		
		log(freshNewProposal);
		if (freshNewProposal.isSolved())
			return freshNewProposal;
		
		return freshNewProposal;
	}

	private SudokuBoard fillUsingSimpleMarkHeuristic(SudokuBoard actualProposal) {
		SudokuBoard solutionProposal = actualProposal.copy();
		boolean changesHappened = false;
		do {
			changesHappened = false;
			for (int number = 1; number <= SudokuBoard.BOARD_SIZE; number++) {
				SudokuBoard markedBoard = solutionProposal.mark(number);
				log(markedBoard);
				for (BoardSector sector: BoardSector.values()) {
					BoardPoint point = markedBoard.eval(number, sector);
					if (point != null) {
						solutionProposal = solutionProposal.set(number, point);
						changesHappened = true;
					}
				}
			}
		} while (changesHappened);
		
		return solutionProposal;
	}

	private SudokuBoard fillOneCellLeftOnSectors(SudokuBoard solutionProposal) {
		for (int number = 1; number <= SudokuBoard.BOARD_SIZE; number++) {
			for (BoardSector sector: BoardSector.values()) {
				BoardPoint targetPoint = solutionProposal.eval(number, sector);
				if (targetPoint != null) {
					return solutionProposal.set(number, targetPoint);
				}
			}
		}
		return solutionProposal;
	}

	private void log(SudokuBoard board) {
		System.out.println(board.print());
	}

}
