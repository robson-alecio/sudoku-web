package sudoku.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class NearToFinishSectorInfo {

	private BoardSector sector;
	private List<Integer> missingNumbers;
	private List<BoardPoint> emptyCells;
	
	public NearToFinishSectorInfo(BoardSector sector, List<Integer> missingNumbers, List<BoardPoint> emptyCells) {
		super();
		this.sector = sector;
		this.missingNumbers = missingNumbers;
		this.emptyCells = emptyCells;
	}

	public BoardSector getSector() {
		return sector;
	}

	public List<Integer> getMissingNumbers() {
		return Collections.unmodifiableList(missingNumbers);
	}

	public List<BoardPoint> getEmptyCells() {
		return Collections.unmodifiableList(emptyCells);
	}

	public List<Map<Integer, BoardPoint>> getCombinations() {
		List<List<Integer>> sequences = generateSequencesOfMissingNumbers();
		
		List<Map<Integer, BoardPoint>> combinations = new ArrayList<>();
		for (List<Integer> sequence : sequences) {
			HashMap<Integer, BoardPoint> combination = new HashMap<>();
			
			LinkedList<BoardPoint> cellsToFill = new LinkedList<>(emptyCells);
			for (Integer number : sequence)
				combination.put(number, cellsToFill.pop());
			
			combinations.add(combination);
		}
		
		return combinations;
	}

	private List<List<Integer>> generateSequencesOfMissingNumbers() {
		List<List<Integer>> sequences = new ArrayList<>();
		
		//????
		
		return sequences;
	}
	
}
