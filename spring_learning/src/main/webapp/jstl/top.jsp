<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<style>
.menu{
	width: 500px;
	height: 30px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-content: center; 
}

.menu > span {
	display : block;
	border : 1px solid black;
	width: 100px;
	height: inherit;
	text-align: center;
	line-height: 30px;
}
</style>
<title>상단 페이지</title>
<%-- <cr:set id="mid" value="<%=mid%>" />--%>
아이디 : ${mid}, 레벨 : ${level}<br>
고객명 : ${param.user}
<div class="menu">
	<span>수강신청</span>
	<span>학습지원센터</span>
	<span>나의강의실${param.z}</span>
</div>
