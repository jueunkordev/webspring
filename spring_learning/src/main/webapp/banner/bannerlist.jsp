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
	<p>전체 등록된 배너 갯수 : ${total}</p>
	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox" id="allck" onclick="check_all(this.checked)"></th>
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
			<cr:set var="ino" value="${total-userpage}"></cr:set> <!-- 게시물 일련번호 셋팅 -->
			<!-- jsp, jstl 반복문 안에는 절대 id로 같은 이름을 사용하시면 안됩니다. -->
			<!-- form id="frm" -->
				<cr:forEach var="bn" items="${all }" varStatus="idx">
					<tr height="50">
					<!-- bidx : DB에서 사용된 auto_increment 값 -->
						<td><input type="checkbox" name="ckbox" value="${bn.bidx}"></td>
						<td align="center">${ino-idx.index}</td>
						<td>${bn.bname}</td>
						<td align="center">
						<cr:if test="${bn.file_url == null}">
							NO IMAGE					
						</cr:if> 
						<cr:if test="${bn.file_url != null}">
								<img src="..${bn.file_url }" style="width: 100px; align-content: center;">
						</cr:if>
						</td>
						<td align="center">
						<a href="../upload/${bn.file_new}" tatget="_blank">${bn.file_ori }</a>
						</td>
						<td align="center">${fn:substring(bn.bdate,0,10) }</td>
					</tr>
				</cr:forEach>
			<!-- /form -->
		</tbody>
	</table>
	<br><br>
	<!-- form 전송으로 선택된 값을 삭제하는 프로세서 -->
	<!-- Front-end 데이터 전송 방식에 따른 처리 방식
		1. forEach => form => 동일한  name => post 전송 => 배열
		2. form => 하나의 hidden을 이용하여 post 전송 => 자료형 한 개로 처리
	 -->
	<form id="dform" method="post" action="./bannerdel">
		<input type="hidden" name="ckdel">
	</form>
	<input type="button" value="선택삭제" onclick="check_del()">
	<br><br>
	
	<!-- 페이징  -->
	<table border="1" cellpadding="0" cellspacing="0">
		<tbody>
			<tr height="30">
				<!-- Controller에서 데이터의 전체 갯수를 받음 해당 값을 한 페이지 당 5개씩 출력하는 구조로 산수식을 입력하여 총 페이징 번호를 생성하여 출력함 -->
				<cr:set var="pageidx" value="${total / 5 + (1-((total / 5) % 1))%1 }"/>
				<cr:forEach var="no" begin="1" end="${pageidx}" step="1">
					<td width="50" align="center" onclick="pg('${no}')">${no}</td>
				</cr:forEach>
			</tr>
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
		
		function pg(no) {
			location.href='./bannerlist?pageno='+no;
		}
		// 전체선택 관련 핸들링 함수
		// getElements : name,class | getElement : id
		function check_all(ck) {
			var ea = document.getElementsByName("ckbox");
			// 조건문 없이 이벤트를 발생시킴
			var w = 0;
			while(w < ea.length){
				ea[w].checked = ck;
				w++;
				}
			}
			/*if(ck==true){ // 전체선택 했을 경우
				var w = 0;
				while (w < ea.length) {
					ea[w].checked = true;
					w++;
				}
			}else{ // 전체선택 해제했을 경우
				var w = 0;
				while (w < ea.length) {
					ea[w].checked = false;
					w++;
			}
		}*/
		// 선택삭제 버튼 클릭 시 리스트에서 체크된 값을 확인 후 배열화하여 hidden으로 값을 적용하여 Back-end로 문자열로 전달
		function check_del() {
			var ar = new Array(); // script 배열
			var ob = document.getElementsByName("ckbox");
			var w = 0;
			while (w < ob.length) {
				if(ob[w].checked == true){
					ar.push(ob[w].value);
				}
				w++;
			}
			dform.ckdel.value = ar;
			if(confirm(" 해당 데이터를 삭제 시 복구되지 않습니다.")){
				dform.submit();
			}
		}
	</script>
</html>







