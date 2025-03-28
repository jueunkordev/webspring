<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	String pdnm = (String)request.getAttribute("pdnm");
	String pcode = (String)request.getAttribute("pcode");
	String pmoney = (String)request.getAttribute("pmoney");
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- Controller(ModelAndView) => jstl 출력 ${} --%>
상품명 : ${pdnm}<br>
상품코드 : ${pcode}<br>
상품가격 : ${pmoney}<br>
</body>
</html>