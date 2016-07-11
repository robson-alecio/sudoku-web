<%@page import="sudoku.solver.SudokuPuzzle"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Solução</title>
<style>
table {
	border-collapse: collapse;
	font-family: Arial, Helvetica, sans-serif;
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
	<%
		SudokuPuzzle puzzle = (SudokuPuzzle) request.getAttribute("puzzle");
		out.print(puzzle.printHtml());
	%>
</body>
</html>