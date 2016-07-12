package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class NearToFinishSectorInfoTest {

	@Test
	public void generateCombinations() {
		List<Integer> missingNumbers = new ArrayList<>();
		missingNumbers.add(2);
		missingNumbers.add(5);
		missingNumbers.add(6);
		
		List<BoardPoint> emptyCells = new ArrayList<>();
		emptyCells.add(new BoardPoint(3, 4));
		emptyCells.add(new BoardPoint(4, 4));
		emptyCells.add(new BoardPoint(5, 5));
		
		List<Map<Integer, BoardPoint>> combinations = new NearToFinishSectorInfo(BoardSector.MIDDLE_CENTER, missingNumbers, emptyCells).getCombinations();
		
		assertThat(combinations).hasSize(6);
		
		Map<Integer, BoardPoint> case1 = combinations.get(0);
		assertPosition(case1, 2, 3, 4);
		assertPosition(case1, 5, 4, 4);
		assertPosition(case1, 6, 5, 5);
		
		Map<Integer, BoardPoint> case2 = combinations.get(1);
		assertPosition(case2, 2, 3, 4);
		assertPosition(case2, 6, 4, 4);
		assertPosition(case2, 5, 5, 5);
		
		Map<Integer, BoardPoint> case3 = combinations.get(2);
		assertPosition(case3, 5, 3, 4);
		assertPosition(case3, 6, 4, 4);
		assertPosition(case3, 2, 5, 5);
		
		Map<Integer, BoardPoint> case4 = combinations.get(3);
		assertPosition(case4, 5, 3, 4);
		assertPosition(case4, 2, 4, 4);
		assertPosition(case4, 6, 5, 5);
		
		Map<Integer, BoardPoint> case5 = combinations.get(4);
		assertPosition(case5, 6, 3, 4);
		assertPosition(case5, 2, 4, 4);
		assertPosition(case5, 5, 5, 5);
		
		Map<Integer, BoardPoint> case6 = combinations.get(5);
		assertPosition(case6, 6, 3, 4);
		assertPosition(case6, 5, 4, 4);
		assertPosition(case6, 2, 5, 5);
	}

	private void assertPosition(Map<Integer, BoardPoint> caseMap, int number, int expectedLine, int expectedColumn) {
		BoardPoint point = caseMap.get(number);
		
		assertThat(point.line).isEqualTo(expectedLine);
		assertThat(point.column).isEqualTo(expectedColumn);
	}
}
