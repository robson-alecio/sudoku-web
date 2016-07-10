package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

import java.util.List;

import org.junit.Test;

public class SudokuBoardTest {
	
	@Test
	public void boardRepresentationEqualsToString() {
		SudokuBoard problem = new SudokuBoard(
				"080409653|642800070|000000800|007005042|000701000|850600100|006000000|010004736|273508010");
		assertThat(problem.toString()).isEqualTo("080409653|642800070|000000800|007005042|000701000|850600100|006000000|010004736|273508010");
		
		SudokuBoard solution = new SudokuBoard("781429653|642853971|935176824|167985342|329741568|854632197|496317285|518294736|273568419");
		assertThat(solution.toString()).isEqualTo("781429653|642853971|935176824|167985342|329741568|854632197|496317285|518294736|273568419");
	}
	
	@Test
	public void impossibleCreateBoardWithNumberRepeatedOnSameRow() {
		try {
			new SudokuBoard(
				"130030301|" +
				"020020004|" +
				"000000000|" +
				"000400400|" +
				"050050050|" +
				"600600600|" +
				"700070007|" +
				"080000008|" +
				"912200109");
			fail("Não deveria ter criado o Board.");
		} catch (InvalidInputBoardException e) {
			List<String> failures = e.getFailures();
			
			assertThat(failures).hasSize(11);
			assertThat(failures.get(0)).isEqualTo("Número 1 aparece 2 vezes na linha 1.");
			assertThat(failures.get(1)).isEqualTo("Número 1 aparece 2 vezes na linha 9.");
			assertThat(failures.get(2)).isEqualTo("Número 2 aparece 2 vezes na linha 2.");
			assertThat(failures.get(3)).isEqualTo("Número 2 aparece 2 vezes na linha 9.");
			assertThat(failures.get(4)).isEqualTo("Número 3 aparece 3 vezes na linha 1.");
			assertThat(failures.get(5)).isEqualTo("Número 4 aparece 2 vezes na linha 4.");
			assertThat(failures.get(6)).isEqualTo("Número 5 aparece 3 vezes na linha 5.");
			assertThat(failures.get(7)).isEqualTo("Número 6 aparece 3 vezes na linha 6.");
			assertThat(failures.get(8)).isEqualTo("Número 7 aparece 3 vezes na linha 7.");
			assertThat(failures.get(9)).isEqualTo("Número 8 aparece 2 vezes na linha 8.");
			assertThat(failures.get(10)).isEqualTo("Número 9 aparece 2 vezes na linha 9.");
		}
	}

	@Test
	public void impossibleCreateBoardWithNumberRepeatedOnSameColumn() {
		try {
			new SudokuBoard(
					"100050009|" +
					"320006000|" +
					"000000701|" + 
					"120400000|" +
					"000050001|" +
					"300006782|" +
					"000400002|" +
					"000006000|" + 
					"300050789");
			fail("Não deveria ter criado o Board.");
		} catch (InvalidInputBoardException e) {
			List<String> failures = e.getFailures();
			
			assertThat(failures).hasSize(11);
			assertThat(failures.get(0)).isEqualTo("Número 1 aparece 2 vezes na coluna 1.");
			assertThat(failures.get(1)).isEqualTo("Número 1 aparece 2 vezes na coluna 9.");
			assertThat(failures.get(2)).isEqualTo("Número 2 aparece 2 vezes na coluna 2.");
			assertThat(failures.get(3)).isEqualTo("Número 2 aparece 2 vezes na coluna 9.");
			assertThat(failures.get(4)).isEqualTo("Número 3 aparece 3 vezes na coluna 1.");
			assertThat(failures.get(5)).isEqualTo("Número 4 aparece 2 vezes na coluna 4.");
			assertThat(failures.get(6)).isEqualTo("Número 5 aparece 3 vezes na coluna 5.");
			assertThat(failures.get(7)).isEqualTo("Número 6 aparece 3 vezes na coluna 6.");
			assertThat(failures.get(8)).isEqualTo("Número 7 aparece 3 vezes na coluna 7.");
			assertThat(failures.get(9)).isEqualTo("Número 8 aparece 2 vezes na coluna 8.");
			assertThat(failures.get(10)).isEqualTo("Número 9 aparece 2 vezes na coluna 9.");
		}
	}
	
	@Test
	public void impossibleCreateBoardWithNumberRepeatedOnSameSector() {
		try {
			new SudokuBoard(
					"100200300|" +
					"000020030|" +
					"001000000|" + 
					"043500006|" +
					"430050060|" +
					"300005600|" +
					"700080009|" +
					"070000012|" + 
					"007800921");
			fail("Não deveria ter criado o Board.");
		} catch (InvalidInputBoardException e) {
			List<String> failures = e.getFailures();
			
			assertThat(failures).hasSize(12);
			assertThat(failures.get(0)).isEqualTo("Número 1 aparece 2 vezes no setor Superior Esquerdo.");
			assertThat(failures.get(1)).isEqualTo("Número 1 aparece 2 vezes no setor Inferior Direito.");
			assertThat(failures.get(2)).isEqualTo("Número 2 aparece 2 vezes no setor Superior Centro.");
			assertThat(failures.get(3)).isEqualTo("Número 2 aparece 2 vezes no setor Inferior Direito.");
			assertThat(failures.get(4)).isEqualTo("Número 3 aparece 2 vezes no setor Superior Direito.");
			assertThat(failures.get(5)).isEqualTo("Número 3 aparece 3 vezes no setor Meio Esquerdo.");
			assertThat(failures.get(6)).isEqualTo("Número 4 aparece 2 vezes no setor Meio Esquerdo.");
			assertThat(failures.get(7)).isEqualTo("Número 5 aparece 3 vezes no setor Meio Centro.");
			assertThat(failures.get(8)).isEqualTo("Número 6 aparece 3 vezes no setor Meio Direito.");
			assertThat(failures.get(9)).isEqualTo("Número 7 aparece 3 vezes no setor Inferior Esquerdo.");
			assertThat(failures.get(10)).isEqualTo("Número 8 aparece 2 vezes no setor Inferior Centro.");
			assertThat(failures.get(11)).isEqualTo("Número 9 aparece 2 vezes no setor Inferior Direito.");
		}
	}
	
	@Test
	public void multiplesInconsistencies() {
		try {
			new SudokuBoard(
					"777000000|" +
					"777000000|" +
					"777000000|" + 
					"000000000|" +
					"000000000|" +
					"000000000|" +
					"000000000|" +
					"000000000|" + 
					"000000000");
			fail("Não deveria ter criado o Board.");
		} catch (InvalidInputBoardException e) {
			List<String> failures = e.getFailures();
			
			assertThat(failures).hasSize(7);
			assertThat(failures.get(0)).isEqualTo("Número 7 aparece 3 vezes na linha 1.");
			assertThat(failures.get(1)).isEqualTo("Número 7 aparece 3 vezes na linha 2.");
			assertThat(failures.get(2)).isEqualTo("Número 7 aparece 3 vezes na linha 3.");
			assertThat(failures.get(3)).isEqualTo("Número 7 aparece 3 vezes na coluna 1.");
			assertThat(failures.get(4)).isEqualTo("Número 7 aparece 3 vezes na coluna 2.");
			assertThat(failures.get(5)).isEqualTo("Número 7 aparece 3 vezes na coluna 3.");
			assertThat(failures.get(6)).isEqualTo("Número 7 aparece 9 vezes no setor Superior Esquerdo.");
		}
	}

	@Test
	public void boardIsSolved() {
		SudokuBoard problem = new SudokuBoard(
				"080409653|642800070|000000800|007005042|000701000|850600100|006000000|010004736|273508010");
		assertThat(problem.isSolved()).isFalse();
		
		SudokuBoard solution = new SudokuBoard("781429653|642853971|935176824|167985342|329741568|854632197|496317285|518294736|273568419");
		assertThat(solution.isSolved()).isTrue();
	}
}
