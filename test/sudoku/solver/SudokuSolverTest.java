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
	public void solveAndSaveNewProblem() {
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

}
