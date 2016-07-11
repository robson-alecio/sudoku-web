package sudoku.solver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SudokuPuzzlePostgresRepository extends SudokuPuzzleRepository {
	
	private Connection connection;

	public SudokuPuzzlePostgresRepository(String host, int port, String database, String user, String password) {
		try {
			Class.forName("org.postgresql.Driver");
			
			String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Falha ao obter conexão.", e); 
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao obter conexão.", e); 
		}
	}

	@Override
	public boolean save(SudokuPuzzle puzzle) {
		try {
			SudokuPuzzle savedPuzzle = find(puzzle.getProblem().getRepresentation());
			if (savedPuzzle != null)
				return false;
			
			PreparedStatement ps = connection.prepareStatement("insert into sudokupuzzles (problem, solution) values (?, ?)");
			
			ps.setString(1, puzzle.getProblem().getRepresentation());
			ps.setString(2, puzzle.getSolution().getRepresentation());
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao salvar puzzle " + puzzle, e);
		} 
	}

	@Override
	public SudokuPuzzle find(String boardRepresentation) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select problem, solution " +
					"from sudokupuzzles " +
					"where problem = ?");
			ps.setString(1, boardRepresentation);
			
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next())
				return null;
			
			String problemRepresentation = rs.getString("problem");
			String solutionRepresentation = rs.getString("solution");
			
			SudokuBoard problem = new SudokuBoard(problemRepresentation);
			SudokuBoard solution = new SudokuBoard(solutionRepresentation);
			
			return new SudokuPuzzle(problem, solution);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public void clear() {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from sudokupuzzles");
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException("Falha ao limpar tabela", e);
		}
	}

}
