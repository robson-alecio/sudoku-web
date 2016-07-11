package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public abstract class SudokuPuzzleRepositoryTest {

	private static final String PROBLEM_REPRESENTATION =  "300000078|000008340|800204500|030080420|200060001|048010030|003805002|059700000|180000004";
	private static final String SOLUTION_REPRESENTATION = "314659278|526178349|897234516|631987425|275463981|948512637|763845192|459721863|182396754";

	protected SudokuPuzzleRepository repository;
	
	private SudokuPuzzle puzzleSpy;
	
	private SudokuBoard problemSpy;
	private SudokuBoard solutionSpy;

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		repository = getRepository();
		repository.clear();

		problemSpy = spy(new SudokuBoard(PROBLEM_REPRESENTATION));
		solutionSpy = spy(new SudokuBoard(SOLUTION_REPRESENTATION));
		
		puzzleSpy = spy(new SudokuPuzzle(problemSpy, solutionSpy));
	}
	
	@After
	public void tearDown() {
		repository.clear();
	}
	
	protected abstract SudokuPuzzleRepository getRepository();
	
	@Test
	public void saveNewPuzzle() {
		assertThat(repository.save(puzzleSpy)).isEqualTo(true);
		
		verify(problemSpy, atLeast(2)).getRepresentation();
		verify(puzzleSpy, atLeast(1)).getProblem();
		verify(puzzleSpy, atMost(1)).getSolution();
		verifyNoMoreInteractions(problemSpy, puzzleSpy);
	}
	
	@Test
	public void canNotSavePuzzleRepeatedly() {
		repository.save(puzzleSpy);
		assertThat(repository.save(puzzleSpy)).isEqualTo(false);
		
		verify(problemSpy, atLeast(3)).getRepresentation();
		verify(puzzleSpy, atLeast(2)).getProblem();
		verify(puzzleSpy, atMost(1)).getSolution();
		verifyNoMoreInteractions(problemSpy, puzzleSpy);
	}
	
	@Test
	public void nullOnSearchNewProblem() {
		assertThat(repository.find(PROBLEM_REPRESENTATION)).isNull();
	}
	
	@Test
	public void findPuzzle() {
		repository.save(puzzleSpy);
		
		SudokuPuzzle recoverdPuzzle = repository.find(PROBLEM_REPRESENTATION);
		assertThat(recoverdPuzzle.getProblem().getRepresentation()).isEqualTo(PROBLEM_REPRESENTATION);
		assertThat(recoverdPuzzle.getSolution().getRepresentation()).isEqualTo(SOLUTION_REPRESENTATION);
	}
}
