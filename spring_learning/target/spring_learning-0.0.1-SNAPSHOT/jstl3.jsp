<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String data[] = new String[]{"hong","kim","park"};
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL 기초3 (반복문 = foreach)</title>
	</head>
	<body>
		<table border=1>
			<tr>
			<%-- forEach 기본형태 구조 
			<!-- forEach : JSTL 유일하게 배열 및 반복문으로 사용하는 속성
			begin : 시작값
			end : 종료값
			 -->
			<cr:forEach var="z" begin="1" end="5">
				<td>${z}</td>
			</cr:forEach>
			--%>
			</tr>
		</table>
	</body>
</html>