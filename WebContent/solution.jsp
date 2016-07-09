<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Solução</title>
</head>
<body>
	<%
	String board = (String)request.getAttribute("board");
	out.print(board);
	%>
</body>
</html>