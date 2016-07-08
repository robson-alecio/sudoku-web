<%@page import="sudoku.solver.DummyBean" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Dummy</title>
</head>
<body>
	<%
	DummyBean bean = (DummyBean)request.getAttribute("bean");
	out.print(bean.getUmaFrase() + " " + bean.getUmNumero());
	%>
</body>
</html>