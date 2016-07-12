package sudoku.solver;

class BoardPoint {
	
	public final int line;
	public final int column;
	
	BoardPoint(int line, int column) {
		super();
		this.line = line;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", line, column);
	}
	
}
