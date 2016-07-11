package sudoku.solver;

public class SudokuPuzzle {
	
	private SudokuBoard problem;
	private SudokuBoard solution;
	
	SudokuPuzzle(SudokuBoard problem, SudokuBoard solution) {
		super();
		this.problem = problem;
		this.solution = solution;
	}
	
	public SudokuBoard getProblem() {
		return problem;
	}
	
	public SudokuBoard getSolution() {
		return solution;
	}
	
	@Override
	public String toString() {
		return String.format("%s -> %s", problem, solution);
	}

	public String printHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		
		sb.append("<tr>");
		sb.append(createCellFor(BoardSector.TOP_LEFT));
		sb.append(createCellFor(BoardSector.TOP_CENTER));
		sb.append(createCellFor(BoardSector.TOP_RIGHT));
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append(createCellFor(BoardSector.MIDDLE_LEFT));
		sb.append(createCellFor(BoardSector.MIDDLE_CENTER));
		sb.append(createCellFor(BoardSector.MIDDLE_RIGHT));
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append(createCellFor(BoardSector.BOTTOM_LEFT));
		sb.append(createCellFor(BoardSector.BOTTOM_CENTER));
		sb.append(createCellFor(BoardSector.BOTTOM_RIGHT));
		sb.append("</tr>");
		
		sb.append("</table>");
		return sb.toString();
	}

	private String createCellFor(BoardSector sector) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<td class=\"").append(sector.getCssClass()).append("\">");
		sb.append("<table class=\"inner-table\">");
		
		for (int line = sector.getStart().line; line <= sector.getEnd().line; line++) {
			sb.append("<tr>");
			for (int column = sector.getStart().column; column <= sector.getEnd().column; column++) {
				int problemValue = problem.getValue(line, column);
				int solutionValue = solution.getValue(line, column);
				
				boolean originalValue = problemValue == solutionValue;
				
				String format = originalValue ? "<b>%d</b>" : "%d";
				String printNumber = String.format(format, solutionValue);
				
				sb.append("<td><center>").append(printNumber).append("</center></td>");
			}
			sb.append("</tr>");
		}
		
		sb.append("</table>");
		sb.append("</td>");
		
		return sb.toString();
	}

}
