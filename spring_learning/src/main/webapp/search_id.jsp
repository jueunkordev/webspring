<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 기능 (member_mapper.xml)</title>
</head>
<body>
	<form id="frm" method="post" action="./idsearch.do">
		고객명 : <input type="text" name="mname"><br>
		이메일 : <input
			type="text" name="memail"><br>
			<input type="checkbox"
			name="mcheck" value="Y">개인 정보 약관 동의<br>
			<input
			type="button" value="아이디 찾기" onclick="gopage()">
	</form>
</body>
<script>
	function gopage() {
		frm.submit();
	}
</script>
</html>