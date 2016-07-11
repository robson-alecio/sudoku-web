package sudoku.solver;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SudokuPuzzleTest {

	@Test
	public void printHtml() {
		SudokuBoard problem = new SudokuBoard(
			"100008009|" +
			"002000008|" +
			"080549000|" + 
			"040200900|" +
			"309000201|" +
			"001005040|" +
			"000912030|" +
			"700000100|" +
			"200700006");
		
		SudokuBoard solution = new SudokuBoard(
			"134628579|" +
			"952137468|" +
			"687549312|" +
			"546271983|" +
			"379486251|" +
			"821395647|" +
			"468912735|" +
			"795863124|" +
			"213754896");
		
		SudokuPuzzle puzzle = new SudokuPuzzle(problem, solution);
		
		assertThat(puzzle.printHtml()).isEqualTo(
				"<center>\n" + 
				"<table><tr><td class=\"normal\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"normal\"><center><b><u>1</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>3</center></td>\n" + 
				"<td class=\"normal\"><center>4</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>9</center></td>\n" + 
				"<td class=\"normal\"><center>5</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>2</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>6</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>8</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>7</center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"alternate\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"alternate\"><center>6</center></td>\n" + 
				"<td class=\"alternate\"><center>2</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>8</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center>1</center></td>\n" + 
				"<td class=\"alternate\"><center>3</center></td>\n" + 
				"<td class=\"alternate\"><center>7</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>5</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>4</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>9</u></b></center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"normal\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"normal\"><center>5</center></td>\n" + 
				"<td class=\"normal\"><center>7</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>9</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>4</center></td>\n" + 
				"<td class=\"normal\"><center>6</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>8</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>3</center></td>\n" + 
				"<td class=\"normal\"><center>1</center></td>\n" + 
				"<td class=\"normal\"><center>2</center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"alternate\"><center>5</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>4</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>6</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>3</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>7</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>9</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center>8</center></td>\n" + 
				"<td class=\"alternate\"><center>2</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>1</u></b></center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"normal\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"normal\"><center><b><u>2</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>7</center></td>\n" + 
				"<td class=\"normal\"><center>1</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>4</center></td>\n" + 
				"<td class=\"normal\"><center>8</center></td>\n" + 
				"<td class=\"normal\"><center>6</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>3</center></td>\n" + 
				"<td class=\"normal\"><center>9</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>5</u></b></center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"alternate\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>9</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>8</center></td>\n" + 
				"<td class=\"alternate\"><center>3</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>2</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>5</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>1</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center>6</center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>4</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>7</center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"normal\"><center>4</center></td>\n" + 
				"<td class=\"normal\"><center>6</center></td>\n" + 
				"<td class=\"normal\"><center>8</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center><b><u>7</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>9</center></td>\n" + 
				"<td class=\"normal\"><center>5</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center><b><u>2</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>1</center></td>\n" + 
				"<td class=\"normal\"><center>3</center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"alternate\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>9</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>1</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center><b><u>2</u></b></center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center>8</center></td>\n" + 
				"<td class=\"alternate\"><center>6</center></td>\n" + 
				"<td class=\"alternate\"><center>3</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"alternate\"><center><b><u>7</u></b></center></td>\n" + 
				"<td class=\"alternate\"><center>5</center></td>\n" + 
				"<td class=\"alternate\"><center>4</center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"<td class=\"normal\">\n" + 
				"<table>\n" + 
				"<tr><td class=\"normal\"><center>7</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>3</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>5</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center><b><u>1</u></b></center></td>\n" + 
				"<td class=\"normal\"><center>2</center></td>\n" + 
				"<td class=\"normal\"><center>4</center></td>\n" + 
				"</tr>\n" + 
				"<tr><td class=\"normal\"><center>8</center></td>\n" + 
				"<td class=\"normal\"><center>9</center></td>\n" + 
				"<td class=\"normal\"><center><b><u>6</u></b></center></td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</td>\n" + 
				"</tr>\n" + 
				"</table>\n" + 
				"</center>");
	}
}
