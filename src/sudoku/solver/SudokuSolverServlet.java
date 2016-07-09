package sudoku.solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SudokuSolverServlet
 */
@WebServlet("/SudokuSolverServlet")
public class SudokuSolverServlet extends HttpServlet {

	private static final String VALOR_INVALIDO_LINHA_COLUNA = "Valor inválido (linha: %d, coluna: %d): Valor deve estar entre 1 e 9, mas é %d.";

	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_CELL_FORMAT = "c%d%d";
	private static final String FALHA_AO_PROCESSAR_VALOR_LINHA_COLUNA = "Falha ao processar valor (linha: %d, coluna: %d): %s";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SudokuSolverServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		List<String> failures = new ArrayList<>();
		String boardRepresentation = getLineRepresentationToBoardFromRequest(request, failures);
		
		if (failures.isEmpty()) {
			try {
				SudokuPuzzle sudokuPuzzle = SudokuSolver.getInstance().solve(boardRepresentation);
				showAnswer(request, response, sudokuPuzzle);
			} catch (InvalidInputBoardException e) {
				showFailures(request, response, e.getFailures());
			}
			
		} else {
			showFailures(request, response, failures);
		}
	}

	private String getLineRepresentationToBoardFromRequest(HttpServletRequest request, List<String> failures) {
		StringBuilder sb = new StringBuilder();
		for (int line = 0; line < 9; line++) {
			for (int column = 0; column < 9; column++) {
				int value = getIntParam(request, failures, line, column);
				sb.append(value);
			}
			
			if (line < 8)
				sb.append("|");
		}
		return sb.toString();
	}

	private int getIntParam(HttpServletRequest request, List<String> failures, int line, int column) {
		String paramName = String.format(PARAM_CELL_FORMAT, line, column);
		String paramValue = request.getParameter(paramName );
		
		if (paramValue == null || paramValue.isEmpty())
			return 0;
		
		try {
			int intValue = Integer.parseInt(paramValue);
			if (intValue < 1 || intValue > 9) {
				failures.add(String.format(VALOR_INVALIDO_LINHA_COLUNA,	line + 1, column + 1, intValue));
				return -2;
			}
			
			return intValue;
		} catch (NumberFormatException e) {
			failures.add(String.format(FALHA_AO_PROCESSAR_VALOR_LINHA_COLUNA, line + 1, column + 1, e.getMessage()));
			return -1;
		}
	}

	private void showAnswer(HttpServletRequest request, HttpServletResponse response, SudokuPuzzle sudokuPuzzle) throws ServletException, IOException {
		request.setAttribute("puzzle", sudokuPuzzle);
		request.getRequestDispatcher("solution.jsp").forward(request, response);
	}

	private void showFailures(HttpServletRequest request, HttpServletResponse response, List<String> failures) throws ServletException, IOException {
		request.setAttribute("failures", failures);
		request.getRequestDispatcher("failures.jsp").forward(request, response);		
	}

}
