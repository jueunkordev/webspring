<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 수정 페이지</title>
</head>
<body>
<script src="./macbook.js?v=1"></script>
<form id="frm" method="post" action="./macbook_modifyok.do">
<!-- 
수정 또는 삭제시 무조건 고유값 필요 
이거 안담으면 안됨 ! 
-->
<input type="hidden" name="midx" value="${onedata.midx}">
<!-- 1. front에서 js로 찍기 -->
과정구분 : 
<select name="class_part">
<option value="온라인 상시과정">온라인 상시과정</option> 
<option value="온라인 정규 과정">온라인 정규 과정</option> 
<option value="집합 과정">집합 과정</option> 
</select><br>

<!-- 
2. back이 JSTL로 찍기
배열이었다면 forEach 사용
지금은 따로따로 되어있어서 각각 집어넣음  
-->
과정 카테고리 : 
<select name="class_cate">
<option value="교육" 
	<cr:if test = "${onedata.class_cate} =='교육'}">selected</cr:if>
	>교육</option>
<option value="보강" 
	<cr:if test = "${onedata.class_cate} =='보강'}">selected</cr:if>
	>보강</option>
<option value="자격증" 
	<cr:if test = "${onedata.class_cate} =='자격증'}">selected</cr:if>
	>자격증</option>
</select><br>

<!-- 
input은 value에
textarea는 태그 사이에 
값 찍기 
-->
과정명 : <input type="text" name="class_name" value="${onedata.class_name}"><br>
학습일수 : <input type="text" name="class_day" value="${onedata.class_day}"><br>
정가 : <input type="text" name="class_price" value="${onedata.class_price}"><br>
수강료 : <input type="text" name="class_sales" value="${onedata.class_sales}"><br>
과정소개 : <textarea cols="100" rows="5" name="class_info">${onedata.class_info}</textarea><br>
강사명 : <input type="text" name="class_teacher" value="${onedata.class_teacher}"><br>
학습목표 : <textarea cols="100" rows="5" name="class_object">${onedata.class_object}</textarea><br>

<!-- 기존에 태그에 넣어져있던 checked 지우기 -->
강의 개강 여부 : 
<input type="radio" value="Y" name="class_use"
<cr:if test = "${onedata.class_use} =='Y'}">checked</cr:if>
>개강
<input type="radio" value="N" name="class_use"
<cr:if test = "${onedata.class_use} =='N'}">checked</cr:if>
>종강<br>

<input type="button" value="강의수정" onclick="save_class()">
</form>
</body>

<script>
//⭐.js 파일에서 Back-end 코드를 사용할 수 없음

window.onload = function(){
	//javascript 변수는 backend의 모든 변수값을 받을 수 있음
	var subject = "${onedata.class_part}";
	//console.log(subject);
	
	var ea = frm.class_part.length;
	console.log("ea : "+ea);
	console.log("value : "+frm.class_part.value);
	console.log("use : "+frm.class_use.value);
	for(var f=0; f<ea; f++){
		if(frm.class_part[f].value == subject){
			frm.class_part[f].selected = "selected";	//체크박스면 checked
		}
	}
	
}

</script>
</html>