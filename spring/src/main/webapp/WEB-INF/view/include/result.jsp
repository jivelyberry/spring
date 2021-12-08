<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert('${msg}'); // '' 안에 내용이 바뀌니까 변할 수 있게 변수로 처리 
	location.href='${url}';
</script>