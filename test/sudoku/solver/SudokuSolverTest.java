package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SudokuSolverTest {
	
	private static final String SOLUTION_REPRESENTATION = 
		"781429653|" +
		"642853971|" +
		"935176824|" + 
		"167985342|" +
		"329741568|" +
		"854632197|" +
		"496317285|" +
		"518294736|" +
		"273568419";
	
	@Mock
	private SudokuPuzzleRepository repositoryMock;
	
	@Spy
	private SudokuSolver targetSpy;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		SudokuPuzzleRepository.setInstance(repositoryMock);
		SudokuSolver.setInstance(targetSpy);
	}

	@Test
	public void findProblemAlreadySolved() {
		String boardRepresentation = "100000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000";
		SudokuPuzzle sudokuPuzzleMock = mock(SudokuPuzzle.class);
		when(repositoryMock.find(boardRepresentation)).thenReturn(sudokuPuzzleMock);
		
		SudokuPuzzle sudokuPuzzle = targetSpy.solve(boardRepresentation);
		
		assertThat(sudokuPuzzle).isSameAs(sudokuPuzzleMock);
		
		verify(repositoryMock).find(boardRepresentation);
		verify(targetSpy).solve(boardRepresentation);
		verifyNoMoreInteractions(repositoryMock, targetSpy);
	}
	
	@Test
	public void solveAndSaveNewProblemFlow() {
		SudokuBoard solutionBoardMock = mock(SudokuBoard.class);
		doReturn(solutionBoardMock).when(targetSpy).generateSolution(any(SudokuBoard.class));
		
		String boardRepresentation = "100000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000";
		SudokuPuzzle sudokuPuzzle = targetSpy.solve(boardRepresentation);
		
		assertThat(sudokuPuzzle.getProblem().getRepresentation())
			.isEqualTo("100000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000");
		assertThat(sudokuPuzzle.getSolution()).isSameAs(solutionBoardMock);
		
		verify(repositoryMock).find(boardRepresentation);
		verify(repositoryMock).save(sudokuPuzzle);
		verify(targetSpy).solve(boardRepresentation);
		verify(targetSpy).generateSolution(sudokuPuzzle.getProblem());
		verifyNoMoreInteractions(repositoryMock, targetSpy);
	}
	
	@Test
	public void solveMissingOneCellOnSectorTopLeft() {
		assertSolve(
			"780429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}

	@Test
	public void solveMissingOneCellOnSectorTopCenter() {
		assertSolve(
			"781409653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorTopRight() {
		assertSolve(
			"781429650|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorMiddleLeft() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"850632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorMiddleCenter() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167980342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorMiddleRight() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741508|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorBottomLeft() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"203568419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorBottomCenter() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273560419");
	}
	
	@Test
	public void solveMissingOneCellOnSectorBottomRight() {
		assertSolve(
			"781429653|" +
			"642853971|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568410");
	}
	
	@Test
	public void solveWithTwoCellsLeftOnSameRow() {
		assertSolve(
			"781429653|" +
			"642853070|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}

	@Test
	public void solveWithTwoCellsLeftOnDifferentRow() {
		assertSolve(
			"781429053|" +
			"642853970|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}

	@Test
	public void solveWithTwoCellsLeftOnSameColumn() {
		assertSolve(
			"781429650|" +
			"642853970|" +
			"935176824|" + 
			"167985342|" +
			"329741568|" +
			"854632197|" +
			"496317285|" +
			"518294736|" +
			"273568419");
	}
	
	@Test
	public void solveEasyProblem() {
		assertSolve(
			"080409653|" +
			"642800070|" +
			"000000800|" + 
			"007005042|" +
			"000701000|" +
			"850600100|" +
			"006000000|" +
			"010004736|" +
			"273508010");
	}

	@Test
	public void solveMediumProblem() {
		assertSolve(
			"130800600|" +
			"002007000|" +
			"000120079|" + 
			"280000000|" +
			"090030010|" +
			"000000023|" +
			"570083000|" +
			"000400900|" +
			"009002067",
			
			"137849652|" +
			"952367184|" +
			"468125379|" + 
			"283751496|" +
			"695234718|" +
			"714698523|" +
			"576983241|" +
			"821476935|" +
			"349512867");
	}

	@Test
	public void solveAnotherMediumProblem() {
		assertSolve(
			"300000078|" +
			"000008340|" +
			"800204500|" + 
			"030080420|" +
			"200060001|" +
			"048010030|" +
			"003805002|" +
			"059700000|" +
			"180000004",
			
			"314659278|" +
			"526178349|" +
			"897234516|" + 
			"631987425|" +
			"275463981|" +
			"948512637|" +
			"763845192|" +
			"459721863|" +
			"182396754");
	}
	
	@Test
	public void solveHardProblem() {
		assertSolve(
			"000030005|" +
			"805029000|" +
			"000000098|" + 
			"002500400|" +
			"600000001|" +
			"009007600|" +
			"420000000|" +
			"000810204|" +
			"100060000",
			
			"961438725|" +
			"835729146|" +
			"274651398|" + 
			"712568439|" +
			"648392571|" +
			"359147682|" +
			"426975813|" +
			"597813264|" +
			"183264957");
	}

	@Test
	//1 e 2 resolvidos
	public void solveHardProblemPartSolved() {
		assertSolve(
			"001038725|" +
			"835729146|" +
			"200051398|" + 
			"012506400|" +
			"600302001|" +
			"009107602|" +
			"426970810|" +
			"000810264|" +
			"100264000",
			
			"961438725|" +
			"835729146|" +
			"274651398|" + 
			"712568439|" +
			"648392571|" +
			"359147682|" +
			"426975813|" +
			"597813264|" +
			"183264957");
	}
	
	private void assertSolve(String boardRepresentation) {
		assertSolve(boardRepresentation, SOLUTION_REPRESENTATION);
	}

	private void assertSolve(String boardRepresentation, String solutionRepresentation) {
		SudokuPuzzle sudokuPuzzle = targetSpy.solve(boardRepresentation);
		
		assertThat(sudokuPuzzle.getProblem().getRepresentation()).isEqualTo(boardRepresentation);
		assertThat(sudokuPuzzle.getSolution().getRepresentation()).isEqualTo(solutionRepresentation);
	}

}
