<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 엔진 -->
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 각종함수 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- jstl Database 관련사항 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- jstl 부가옵션(금액,날짜정보,시간,통화기호) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String user = "홍길동"; // JSP 변수
	HttpSession hs = request.getSession();
	hs.setAttribute("ssdata", "15881004");
	
	// jsp session 출력
	String se = (String)hs.getAttribute("ssdata");
	out.print(se);
	
	String tels = (String)hs.getAttribute("tel");
	out.print(tels);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL 기초문법1 (set + session)</title>
	</head>
	<body>
		<!-- jstl 태그 형태 -->
		<!-- core 태그 속성 중 set(setAttribute)와 동일한 형태이며, var 변수생성 value 값 -->
		<cr:set var="a" value="강감찬"></cr:set>
		<input type="text" name="mname" value="${a}">
		<cr:set var="nm" value="<%=user%>"></cr:set>
		고객명 : ${nm}
		<cr:out value="값출력합니다"></cr:out>
		<br><br>
		<p>JSP Session값</p>
		<!-- JSTL로 세선을 생성하는 방식 scope : session, request, page -->
		<cr:set var="tel" value="0210041004" scope="session"></cr:set>
		JSP에 session 데이터 : ${ssdata}<br><br>
		세션 데이터 : ${tel}<br><br><br><br>
		<%
			String money = "50000";
		%>
		<cr:set var="kk" scope="request"><%=money%></cr:set>${kk}
	</body>
</html>






