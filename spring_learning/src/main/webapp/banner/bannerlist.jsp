<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너 리스트 페이지</title>
</head>
<body>

	<form id="sform" method="get" action="./bannerlist" onsubmit="return spage()">
		<P>배너명 검색 : <input type="text" name="search" value="${search}"><input type="submit" value="검색"><input type="button" value="전체목록" onclick="location.href='./bannerlist';"></P>
	</form>
	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th width="80">번호</th>
				<th width="300">배너명</th>
				<th width="100">이미지</th>
				<th width="150">파일명</th>
				<th width="150">등록일</th>
			</tr>
		</thead>
		<!-- 배열값을 조건문을 jstl에서 처리시 functions 이용하여 length로 검토하여 처리합니다. -->
		<cr:if test="${fn:length(all) == 0}">
		     <tbody>
		        <tr height="50">
		           <td colspan="6" align="center">검색된 데이터가 없습니다.</td>
		        </tr>
		     </tbody>
	     </cr:if>
		<tbody>
			<cr:forEach var="bn" items="${all }">
				<tr height="50">
					<td><input type="checkbox"></td>
					<td></td>
					<td>${bn.bname }</td>
					<td>
					<cr:if test="${bn.file_url == null}">
						NO IMAGE					
					</cr:if> 
					<cr:if test="${bn.file_url != null}">
							<img src="..${bn.file_url }" style="width: 100px;">
					</cr:if>
					</td>
					<td align="center">
					<a href="../upload/${bn.file_new}" tatget="_blank">${bn.file_ori }</a>
					</td>
					<td align="center">${fn:substring(bn.bdate,0,10) }</td>
				</tr>
			</cr:forEach>
		</tbody>

		
	</table>
</body>
	<script>
		function spage() {
			if(sform.search.value==""){
				alert("배너명을 입력하세요");
				return false;
			}
			else{
				return;
			}
		}
	</script>
</html>