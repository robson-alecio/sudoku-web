<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sudoku Web - Robson Alécio</title>
<style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
}
td {
	padding: 10px;
	background-color: #ccffe6;
}
.alternate {
	background-color: #ccccff;
}
</style>
</head>
<body>
	<center>
		<h2>Sudoku</h2>
	</center>
	<p />
	<center>
		<h4>Entrada de dados:</h4>
	</center>
	<form action="SudokuSolverServlet" method="post">
		<center>
			<table border="1px solid black">
				<tbody>
					<tr>
						<td>
							<input type="number" min="1" max="9" name="c00"/>
							<input type="number" min="1" max="9" name="c01"/>
							<input type="number" min="1" max="9" name="c02"/><br/>
							<input type="number" min="1" max="9" name="c10"/>
							<input type="number" min="1" max="9" name="c11"/>
							<input type="number" min="1" max="9" name="c12"/><br/>
							<input type="number" min="1" max="9" name="c20"/>
							<input type="number" min="1" max="9" name="c21"/>
							<input type="number" min="1" max="9" name="c22"/>
						</td>
						<td class="alternate">
							<input type="number" min="1" max="9" name="c03"/>
							<input type="number" min="1" max="9" name="c04"/>
							<input type="number" min="1" max="9" name="c05"/><br/>
							<input type="number" min="1" max="9" name="c13"/>
							<input type="number" min="1" max="9" name="c14"/>
							<input type="number" min="1" max="9" name="c15"/><br/>
							<input type="number" min="1" max="9" name="c23"/>
							<input type="number" min="1" max="9" name="c24"/>
							<input type="number" min="1" max="9" name="c25"/>
						</td>
						<td>
							<input type="number" min="1" max="9" name="c06"/>
							<input type="number" min="1" max="9" name="c07"/>
							<input type="number" min="1" max="9" name="c08"/><br/>
							<input type="number" min="1" max="9" name="c16"/>
							<input type="number" min="1" max="9" name="c17"/>
							<input type="number" min="1" max="9" name="c18"/><br/>
							<input type="number" min="1" max="9" name="c26"/>
							<input type="number" min="1" max="9" name="c27"/>
							<input type="number" min="1" max="9" name="c28"/>
						</td>
					</tr>
					<tr>
						<td class="alternate">
							<input type="number" min="1" max="9" name="c30"/>
							<input type="number" min="1" max="9" name="c31"/>
							<input type="number" min="1" max="9" name="c32"/><br/>
							<input type="number" min="1" max="9" name="c40"/>
							<input type="number" min="1" max="9" name="c41"/>
							<input type="number" min="1" max="9" name="c42"/><br/>
							<input type="number" min="1" max="9" name="c50"/>
							<input type="number" min="1" max="9" name="c51"/>
							<input type="number" min="1" max="9" name="c52"/>
						</td>
						<td>
							<input type="number" min="1" max="9" name="c33"/>
							<input type="number" min="1" max="9" name="c34"/>
							<input type="number" min="1" max="9" name="c35"/><br/>
							<input type="number" min="1" max="9" name="c43"/>
							<input type="number" min="1" max="9" name="c44"/>
							<input type="number" min="1" max="9" name="c45"/><br/>
							<input type="number" min="1" max="9" name="c53"/>
							<input type="number" min="1" max="9" name="c54"/>
							<input type="number" min="1" max="9" name="c55"/>
						</td>
						<td class="alternate">
							<input type="number" min="1" max="9" name="c36"/>
							<input type="number" min="1" max="9" name="c37"/>
							<input type="number" min="1" max="9" name="c38"/><br/>
							<input type="number" min="1" max="9" name="c46"/>
							<input type="number" min="1" max="9" name="c47"/>
							<input type="number" min="1" max="9" name="c48"/><br/>
							<input type="number" min="1" max="9" name="c56"/>
							<input type="number" min="1" max="9" name="c57"/>
							<input type="number" min="1" max="9" name="c58"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="number" min="1" max="9" name="c60"/>
							<input type="number" min="1" max="9" name="c61"/>
							<input type="number" min="1" max="9" name="c62"/><br/>
							<input type="number" min="1" max="9" name="c70"/>
							<input type="number" min="1" max="9" name="c71"/>
							<input type="number" min="1" max="9" name="c72"/><br/>
							<input type="number" min="1" max="9" name="c80"/>
							<input type="number" min="1" max="9" name="c81"/>
							<input type="number" min="1" max="9" name="c82"/>
						</td>
						<td class="alternate">
							<input type="number" min="1" max="9" name="c63"/>
							<input type="number" min="1" max="9" name="c64"/>
							<input type="number" min="1" max="9" name="c65"/><br/>
							<input type="number" min="1" max="9" name="c73"/>
							<input type="number" min="1" max="9" name="c74"/>
							<input type="number" min="1" max="9" name="c75"/><br/>
							<input type="number" min="1" max="9" name="c83"/>
							<input type="number" min="1" max="9" name="c84"/>
							<input type="number" min="1" max="9" name="c85"/>
						</td>
						<td>
							<input type="number" min="1" max="9" name="c66"/>
							<input type="number" min="1" max="9" name="c67"/>
							<input type="number" min="1" max="9" name="c68"/><br/>
							<input type="number" min="1" max="9" name="c76"/>
							<input type="number" min="1" max="9" name="c77"/>
							<input type="number" min="1" max="9" name="c78"/><br/>
							<input type="number" min="1" max="9" name="c86"/>
							<input type="number" min="1" max="9" name="c87"/>
							<input type="number" min="1" max="9" name="c88"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<th colspan="3" align="center"><input type="submit"
						value="Processar" /></th>
				</tfoot>
			</table>
		</center>
	</form>
</body>
	</html>
</jsp:root>