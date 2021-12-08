<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	function ajaxTest() {
		$.ajax({
			url : "index.do",
			success : function(r) {
				$("#area").html(r);
			}
		});
	}
	function ajaxTest2() {
		$.ajax({
			url : "detail.do?boardno=44",
			success : function(r) {
				$("#area").html(r);
			}
		});
	}
</script>
</head>
<body>
<input type="button" value="게시판 ajax로 가져오기" onclick="ajaxTest();">
<input type="button" value="게시판 ajax로 가져오기" onclick="ajaxTest2();">
<div id="area"></div>
</body>
</html>