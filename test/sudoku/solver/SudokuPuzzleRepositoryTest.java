package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public abstract class SudokuPuzzleRepositoryTest {

	private static final String BOARD_REPRESENTATION = "100000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000";

	private SudokuPuzzleRepository repository;
	
	@Mock
	private SudokuPuzzle puzzleMock;
	
	@Mock
	private SudokuBoard problemBoardMock;

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		repository = getRepository();

		when(problemBoardMock.getRepresentation()).thenReturn(BOARD_REPRESENTATION);
		when(puzzleMock.getProblem()).thenReturn(problemBoardMock);
	}
	
	protected abstract SudokuPuzzleRepository getRepository();
	
	@Test
	public void saveNewPuzzle() {
		assertThat(repository.save(puzzleMock)).isEqualTo(true);
		
		verify(problemBoardMock, atLeast(2)).getRepresentation();
		verify(puzzleMock, atLeast(1)).getProblem();
		verifyNoMoreInteractions(problemBoardMock, puzzleMock);
	}
	
	@Test
	public void canNotSavePuzzleRepeatedly() {
		repository.save(puzzleMock);
		assertThat(repository.save(puzzleMock)).isEqualTo(false);
		
		verify(problemBoardMock, atLeast(3)).getRepresentation();
		verify(puzzleMock, atLeast(2)).getProblem();
		verifyNoMoreInteractions(problemBoardMock, puzzleMock);
	}
	
	@Test
	public void nullOnSearchNewProblem() {
		assertThat(repository.find(BOARD_REPRESENTATION)).isNull();
	}
	
	@Test
	public void findPuzzle() {
		repository.save(puzzleMock);
		
		assertThat(repository.find(BOARD_REPRESENTATION)).isSameAs(puzzleMock);
	}
}
