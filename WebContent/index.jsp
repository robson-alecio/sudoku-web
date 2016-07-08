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
</head>
<body>
	<center><h2>Sudoku</h2></center>
	<p/>
	<center><h4>Entrada de dados:</h4></center>
	<form action="SudokuSolverServlet" method="post">
		Frase: <input type="text" name="frase"/><br/>  
		Numero: <input type="number" name="numero"/><br/>  
		<input type="submit" value="Faz a mágica"/>  
	</form>
</body>
</html>
</jsp:root>