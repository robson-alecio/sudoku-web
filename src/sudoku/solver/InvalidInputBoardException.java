package sudoku.solver;

import java.util.List;

public class InvalidInputBoardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<String> failures;

	public InvalidInputBoardException(List<String> failures) {
		super(String.format("Houve %d falha(s) ao processar a entrada informada.", failures.size()));
		this.failures = failures;
	}
	
	public List<String> getFailures() {
		return failures;
	}

}
