package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SudokuSolverServletTest {

	@Mock
	private HttpServletRequest requestMock;
	
	@Mock
	private RequestDispatcher requestDispatcherMock;
	
	@Mock
	private HttpServletResponse responseMock;
	
	@Mock
	private SudokuSolver sudokuSolverMock;
	
	private SudokuSolverServlet target;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(requestDispatcherMock);
		
		SudokuSolver.setInstance(sudokuSolverMock);
		
		target = new SudokuSolverServlet();
	}
	
	@Test
	public void goToSolution() throws ServletException, IOException {
		when(requestMock.getParameter("c00")).thenReturn("7");
		when(requestMock.getParameter("c02")).thenReturn("1");
		
		target.doPost(requestMock, responseMock);
		
		verify(responseMock).setContentType("text/html");
		verify(requestMock, times(81)).getParameter(anyString());
		verify(requestMock).setAttribute(eq("puzzle"), any(SudokuPuzzle.class));
		verify(requestMock).getRequestDispatcher("solution.jsp");
		verify(requestDispatcherMock).forward(requestMock, responseMock);
		verify(sudokuSolverMock).solve("701000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000");
		verifyNoMoreInteractions(requestMock, requestDispatcherMock, responseMock, sudokuSolverMock);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void goToFailures() throws ServletException, IOException {
		when(requestMock.getParameter("c00")).thenReturn("a");
		when(requestMock.getParameter("c02")).thenReturn("b");
		when(requestMock.getParameter("c04")).thenReturn("0");
		when(requestMock.getParameter("c15")).thenReturn("-1");
		when(requestMock.getParameter("c26")).thenReturn("10");
		when(requestMock.getParameter("c37")).thenReturn("*");
		
		target.doPost(requestMock, responseMock);
		
		verify(responseMock).setContentType("text/html");
		verify(requestMock, times(81)).getParameter(anyString());
		
		ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
		
		verify(requestMock).setAttribute(eq("failures"), captor.capture());
		verify(requestMock).getRequestDispatcher("failures.jsp");
		verify(requestDispatcherMock).forward(requestMock, responseMock);
		verifyNoMoreInteractions(requestMock, requestDispatcherMock, responseMock, sudokuSolverMock);
		
		Object value = captor.getValue();
		assertThat(value).isInstanceOf(List.class);
		
		List<String> listFailures = (List<String>) value;
		assertThat(listFailures).hasSize(6);
		assertThat(listFailures.get(0)).isEqualTo("Falha ao processar valor (linha: 1, coluna: 1): For input string: \"a\"");
		assertThat(listFailures.get(1)).isEqualTo("Falha ao processar valor (linha: 1, coluna: 3): For input string: \"b\"");
		assertThat(listFailures.get(2)).isEqualTo("Valor inválido (linha: 1, coluna: 5): Valor deve estar entre 1 e 9, mas é 0.");
		assertThat(listFailures.get(3)).isEqualTo("Valor inválido (linha: 2, coluna: 6): Valor deve estar entre 1 e 9, mas é -1.");
		assertThat(listFailures.get(4)).isEqualTo("Valor inválido (linha: 3, coluna: 7): Valor deve estar entre 1 e 9, mas é 10.");
		assertThat(listFailures.get(5)).isEqualTo("Falha ao processar valor (linha: 4, coluna: 8): For input string: \"*\"");
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void goToFailuresForBoardInitialization() throws ServletException, IOException {
		when(sudokuSolverMock.solve(anyString())).thenThrow(new InvalidInputBoardException(Arrays.asList("Erro 1", "Erro 2")));

		target.doPost(requestMock, responseMock);
		
		verify(responseMock).setContentType("text/html");
		verify(requestMock, times(81)).getParameter(anyString());
		
		ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
		
		verify(requestMock).setAttribute(eq("failures"), captor.capture());
		verify(requestMock).getRequestDispatcher("failures.jsp");
		verify(requestDispatcherMock).forward(requestMock, responseMock);
		verify(sudokuSolverMock).solve("000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000|000000000");
		verifyNoMoreInteractions(requestMock, requestDispatcherMock, responseMock, sudokuSolverMock);

		Object value = captor.getValue();
		assertThat(value).isInstanceOf(List.class);
		
		List<String> listFailures = (List<String>) value;
		assertThat(listFailures).hasSize(2);
		assertThat(listFailures.get(0)).isEqualTo("Erro 1");
		assertThat(listFailures.get(1)).isEqualTo("Erro 2");
	}
	
}
