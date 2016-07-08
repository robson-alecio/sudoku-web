package sudoku.solver;

import java.io.IOException;

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
	private static final long serialVersionUID = 1L;
       
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
		
		String frase = request.getParameter("frase");
		int numero = Integer.parseInt(request.getParameter("numero"));
		
		DummyBean bean = new DummyBean();
		bean.setUmaFrase(String.format("<h1>%s</h1>", frase));
		bean.setUmNumero(numero);
		
		request.setAttribute("bean", bean);
		
		request.getRequestDispatcher("dummy.jsp").forward(request, response);
	}

}
