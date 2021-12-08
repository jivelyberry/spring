<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function del(){ //삭제하기 전 물어보는 것 
	if (confirm("삭제하시겠습니까?..안돼에...")){
		location.href="delete.do?boardno=${data.boardno}";
	}
}
</script>
</head>
<body>
<h2>게시판 상세</h2>
<form action="insert.do" method="post">
<table border="1">
	<tr>
		<td>제목</td>
		<td>${data.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${data.content }</textarea></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${data.writer }</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${data.regdate }</td>
	</tr>
	<c:if test="${!empty data.filename }">
	<tr>
		<td>이미지</td>
		<td>
			<img src = "/spring/upload/${data.filename }">
		</td>
	</tr>
	</c:if>
	<tr>
		<td colspan="2">
		<input type="button" value="수정" onclick="location.href='edit.do?boardno=${data.boardno}';">	
		<input type="button" value="삭제" onclick="del();"></td>
	</tr>
</table>
</form>
</body>
</html>