<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pdnm = (String)request.getAttribute("pdnm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product_list.jsp</title>
</head>
<body>
상품명 : <%=pdnm%><br>
</body>
</html>