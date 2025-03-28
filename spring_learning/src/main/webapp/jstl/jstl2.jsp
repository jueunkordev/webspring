<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL 기초2(if문)</title>
	</head>
	<body>
		<cr:if test="${5 < 10}" var="result"> <!-- result : true, false 결과 -->
			
		</cr:if>
		${result}<br>
		<!-- jstl if문 (lt(<), gt(>), le(<=), ge(>=)) -->
		<cr:set var="a" value="56"/>
		<fmt:parseNumber value="${a}" type="number" var="aa"  />
		<cr:set var="b" value="410"/>
		<fmt:parseNumber value="${b}" type="number" var="bb"  />
		${aa}
		${bb}
		<cr:if test="${aa gt bb}">
			a값이 큽니다
		</cr:if>
		<cr:if test="${aa lt bb}">
			b값이 큽니다
		</cr:if>
		<cr:if test="${aa eq bb}">
			동일한 값입니다.
		</cr:if>
		<br><br><br>
		<!-- eq(==), ne(!=), or(||), and(&&) -->
		<cr:set var="product" value="그래픽카드"/>
		<cr:set var="product2" value="모니터"/>
		<cr:if test="${product != '그래픽카드' or product2 == '모니터'}">
			가격미정
		</cr:if>
		<br><br><br>
		<!-- choose, when, otherwise -->
		<cr:set var="agree" value="N"/>
		<cr:choose> <!-- 조건문 시작 설정 -->
			<cr:when test="${agree == 'Y'}"> <!-- else if -->
				약관에 동의하였음
			</cr:when>
			<cr:when test="${agree == 'N'}">
				약관 비동의
			</cr:when>
			<cr:otherwise> <!-- else -->
				해당 약관정보를 확인 못 함
			</cr:otherwise>
		</cr:choose>
	</body>
</html>








