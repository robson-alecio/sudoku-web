package sudoku.solver;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		
		solutionProposal = fillOneCellLeftOnSectors(solutionProposal);
		
		log(solutionProposal);
		if (solutionProposal.isSolved())
			return solutionProposal;

		solutionProposal = fillUsingSimpleMarkHeuristic(solutionProposal);
		
		log(solutionProposal);
		if (solutionProposal.isSolved())
			return solutionProposal;
		
		solutionProposal = fillUsingLinesAndColumnInferedHeuristic(solutionProposal);
		
		log(solutionProposal);
		if (solutionProposal.isSolved())
			return solutionProposal;
		
		solutionProposal = fillUsingCombinationsOnMostFinishedSectorHeuristic(solutionProposal);
		if (solutionProposal.isSolved())
			return solutionProposal;
		
		return solutionProposal;
	}

	private SudokuBoard fillOneCellLeftOnSectors(SudokuBoard solutionProposal) {
		for (int number = 1; number <= SudokuBoard.BOARD_SIZE; number++) {
			solutionProposal = fillOnCellLeftWith(number, solutionProposal);
		}
		return solutionProposal;
	}
	
	private SudokuBoard fillOnCellLeftWith(int number, SudokuBoard actualProposal) {
		return fillOnCellLeftWith(number, actualProposal, actualProposal);
	}
	
	private SudokuBoard fillOnCellLeftWith(int number, SudokuBoard actualProposal, SudokuBoard evalBoard) {
		SudokuBoard solutionProposal = actualProposal.copy();
		
		for (BoardSector sector: BoardSector.values()) {
			BoardPoint targetPoint = evalBoard.eval(number, sector);
			if (targetPoint != null) {
				solutionProposal = solutionProposal.set(number, targetPoint);
			}
		}
		
		return solutionProposal;
	}

	private SudokuBoard fillUsingSimpleMarkHeuristic(SudokuBoard actualProposal) {
		SudokuBoard solutionProposal = actualProposal.copy();
		
		String initialRepresentation = solutionProposal.getRepresentation();
		do {
			initialRepresentation = solutionProposal.getRepresentation();
			for (int number = 1; number <= SudokuBoard.BOARD_SIZE; number++) {
				SudokuBoard markedBoard = solutionProposal.simpleMark(number);
				log(number, markedBoard);
				solutionProposal = fillOnCellLeftWith(number, solutionProposal, markedBoard);
			}
		} while (!solutionProposal.getRepresentation().equals(initialRepresentation));
		
		return solutionProposal;
	}

	private SudokuBoard fillUsingLinesAndColumnInferedHeuristic(SudokuBoard actualProposal) {
		SudokuBoard solutionProposal = actualProposal.copy();
		
		String initialRepresentation = solutionProposal.getRepresentation();
		do {
			initialRepresentation = solutionProposal.getRepresentation();
			for (int number = 1; number <= SudokuBoard.BOARD_SIZE; number++) {
				SudokuBoard markedBoard = solutionProposal.simpleMark(number);
				log(number, markedBoard);
				solutionProposal = fillOnCellLeftWith(number, solutionProposal, markedBoard);
				log(solutionProposal);
				
				solutionProposal = applyRecursiveInference(solutionProposal, number);
				log(solutionProposal);
			}
		} while (!solutionProposal.getRepresentation().equals(initialRepresentation));
	
		return solutionProposal;
	}

	private SudokuBoard fillUsingCombinationsOnMostFinishedSectorHeuristic(SudokuBoard solutionProposal) {
		NearToFinishSectorInfo candidateSectorInfo = solutionProposal.findNearToFinishSector();
		List<Map<Integer, BoardPoint>> combinations = candidateSectorInfo.getCombinations();
		System.out.println("Antes das combinações...");
		log(solutionProposal);
		for (Map<Integer, BoardPoint> combination : combinations) {
			SudokuBoard combinationBoard = solutionProposal.copy();
			for (Entry<Integer, BoardPoint> entry : combination.entrySet()) {
				combinationBoard = combinationBoard.set(entry.getKey(), entry.getValue());
			}
			
			SudokuBoard result = generateSolution(combinationBoard);
			if (result.isSolved())
				return result;
		}
		return solutionProposal;
	}

	private SudokuBoard applyRecursiveInference(SudokuBoard solutionProposal, int number) {
		List<SudokuBoard> crossedInferedBoards = solutionProposal.simpleMark(number).inferRecursiveAndMark(number);
		for (SudokuBoard inferedColumnsMarkedBoard : crossedInferedBoards) {
			log(number, inferedColumnsMarkedBoard);
			solutionProposal = fillOnCellLeftWith(number, solutionProposal, inferedColumnsMarkedBoard);
			log(solutionProposal);
		}
		return solutionProposal;
	}
	
	private void log(SudokuBoard board) {
		System.out.println("=====================================");
		System.out.println(board.print());
	}
	
	private void log(int number, SudokuBoard board) {
		System.out.println("=====================================");
		System.out.printf("Analizando numero: %d\n", number);
		System.out.println(board.print());
	}

}
