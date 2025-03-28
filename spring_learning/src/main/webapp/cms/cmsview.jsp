<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상담신청(CMS) 페이지</title>
</head>
<body>
    제목 : ${csubject}<br>
    상담자명 : ${cuser}<br>
    <!-- disabled : 해당 내용을 더 이상 수정 및 변경을 못하게 하는 속성 (checkbox, radio, input
    단점 : 예를 들어서 name 속성 있어서 Back-end로 값을 전송할 때 disable 처리시 값을 전송하지 않음
    
    contains : 해당 단어가 포함되어 있는지를 확인하는 함수입니다.
     -->
    상담목적 :
	<input type="checkbox"  value="cms1" <cr:if test="${fn:contains(cate, 'cms1')}"> checked </cr:if> disabled> 심리상담 
	<input type="checkbox"  value="cms2" <cr:if test="${fn:contains(cate, 'cms2')}"> checked </cr:if>  disabled> 학교생활
	<input type="checkbox"  value="cms3" <cr:if test="${fn:contains(cate, 'cms3')}"> checked </cr:if> disabled> 가정
	<input type="checkbox"  value="cms4" <cr:if test="${fn:contains(cate, 'cms4')}"> checked </cr:if> disabled> 학업 및 진로
	<input type="checkbox"  value="cms5" <cr:if test="${fn:contains(cate, 'cms5')}"> checked </cr:if> disabled> 대인관계
</body>
</html>
