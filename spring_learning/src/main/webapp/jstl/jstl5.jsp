<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL 기초 - (Database연결)</title>
	</head>
	<body>
		
		<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" 
		url="jdbc:mysql://localhost:3306/mrp" user="project" password="p402402"/>
		<%--ddl1 
		<sql:query var="ps" sql="select * from event" dataSource="${db}"></sql:query>--%>
		<%-- ddl2 --%>
		<cr:set var="tables" value="event"/>
		<sql:query var="ps" dataSource="${db}">
			select * from ${tables}
		</sql:query>
		<!-- rows : Database의 rows의 전체값을 말합니다
			fn은 결과값에 옵션 형태로 함수를 적용하여 결과에 대한 사항을 변화시킴 (태그가 없음)
		-->
		<cr:forEach var="row" items="${ps.rows}">
			고객명 : ${row['ename']}, 
			이메일 : ${row['email']}, 
			등록일 : ${fn:substring(row['ejoin'],0,10)} <br>
		</cr:forEach>
		
	</body>
</html>