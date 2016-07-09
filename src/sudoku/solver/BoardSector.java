package sudoku.solver;

enum BoardSector {
	
	TOP_LEFT("Superior Esquerdo", new BoardPoint(0, 0), new BoardPoint(2, 2)),
	TOP_CENTER("Superior Centro", new BoardPoint(0, 3), new BoardPoint(2, 5)),
	TOP_RIGHT("Superior Direito", new BoardPoint(0, 6), new BoardPoint(2, 8)),
	MIDDLE_LEFT("Meio Esquerdo", new BoardPoint(3, 0), new BoardPoint(5, 2)),
	MIDDLE_CENTER("Meio Centro", new BoardPoint(3, 3), new BoardPoint(5, 5)),
	MIDDLE_RIGHT("Meio Direito", new BoardPoint(3, 6), new BoardPoint(5, 8)),
	BOTTOM_LEFT("Inferior Esquerdo", new BoardPoint(6, 0), new BoardPoint(8, 2)),
	BOTTOM_CENTER("Inferior Centro", new BoardPoint(6, 3), new BoardPoint(8, 5)),
	BOTTOM_RIGHT("Inferior Direito", new BoardPoint(6, 6), new BoardPoint(8, 8));

	private String description;
	private BoardPoint start;
	private BoardPoint end;

	private BoardSector(String description, BoardPoint start, BoardPoint end) {
		this.description = description;
		this.start = start;
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public BoardPoint getStart() {
		return start;
	}

	public BoardPoint getEnd() {
		return end;
	}
}
