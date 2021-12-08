<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	function formCheck(){
		if($("#id").val() == ''){
			alert('아이디를 입력하세요');
			$("#id").focus();
			return false;
		}
		// 아이디 중복체크 
		var con = true;
		$.ajax ({
			url:"idcheck.do", 
			data:{ //파라미터 
				id: $("#id").val() // 버튼을 클릭하는 순간 id에 값을 넣어줌 
			},
			//async:false, // 싱크가 안맞음.... 싱크를 줘야 차례대로 된다...네염.. 
			method:"get", // getmapping 
			success:function(r) { // 익명함수(function) 성공했을때 실행되어질 함수   
				//console.log(r); // 화면에 출력 (r: 응답받은 결과)
				if (r.trim() == '1'){
					alert ('아이디가 중복되었습니다. 다른 아이디를 입력하세요.');
					$("#id").val('');
					$("#id").focus();
					//return false;
					con = false;
				} 
			}
		});
		if (con == false ) return false;
		if ($("#pwd").val()==''){
			alert('비밀번호를 입력하세요');
			$("#pwd").focus();
			return false;
		}
		if ($("#pwd").val() != $("#pwd2").val()){
			alert('비밀번호값을 확인하세요');
			$("#pwd2").focus();
			return false;
		}
		if ($("#name").val() ==''){
			alert('이름을 입력하세요');
			$("#name").focus();
			return false;
		}
		// 학교정보체크 
		$("input[name='school']").each(function(idx){ // input태그의 name 속성의 값이 school 선택자의 객체를 반복 
			//console.log($("input[name='school']").eq(idx).val());
			if ($("input[name='school']").eq(idx).val() =='') {
			alert(idx+1 + '번째 학교명을 입력해 주세요 ');
			$("input[name='school']").eq(idx).focus();
			con = false;
			return false;
			}
			if ($("input[name='year']").eq(idx).val() =='') {
				alert(idx+1 + '번째 졸업년도를 입력해 주세요 ');
				$("input[name='year']").eq(idx).focus();
				con = false;
				return false;
			}
		});
		if (con == false) return false;
		return true;
	}
	
	function idcheck() {
		if ($("#id").val() == ''){
			alert('아이디를 입력하세요');
		} else {
			/*
			웹은 기본적으로 동기적으로 통신함
			클릭하면 이동하면서 페이지 새로열리고, 처음부터 전체를 서버로부터 새로 요청해서 응답받아 화면에 뿌려지는 방식
			근데 비동기는 화면은 그대로고 뒤에서 요청이 일어나서 매번 페이지를 새로 요청할필요 없음
			즉, 바뀌는 부분만 새로운 응답결과로 바꿀수 있음
			*/ 
			$.ajax ({ // 비동기통신(ajax) : 뒤에서 실행되는 방식 $:jQuery 객체 // ajax (콜백함수)
				url:"idcheck.do", // 주소 
				data:{ //파라미터 
					id: $("#id").val() // 버튼을 클릭하는 순간 id에 값을 넣어줌  // id 가 id인 벨류값을 가져옴 
				},
				async:false, // 실행을 순서대로 처리해주도록(true로 주면 순차적으로 실행되지 않고 독립적으로 실행됨)
				method:"get", // 전송방식 : get  
				success:function(r) { // 익명함수(function) 비동기요청이 성공했을때 실행되어질 함수   
					//console.log(r); // 화면에 출력 (r: 응답받은 결과)
					if (r.trim() == '1'){ // trim() : 문자열 좌우에서 공백을 제거하는 함수
						alert ('아이디가 중복되었습니다. 다른 아이디를 입력하세요.');
						$("#id").val(''); // 아이디가 id인 객체의 value값에 ''을 넣는다 (중복이면 아이디 입력란이 공백)
						$("#id").focus(); // focus(): 깜빡깜빡 커서 바로 입력될 수 있게 해주는것 
					} else {
						alert('사용가능한 아이디입니다.');
					}
				}
			});
		};
	}
	
	$(function() {
		$("#id").keyup(function(){
			$.ajax ({
				url:"idcheck.do", 
				data:{ //파라미터 
					id: $("#id").val() // 버튼을 클릭하는 순간 id에 값을 넣어줌 
				},
				async:false, // 실행의 순서의 흐름을 바꿔준다고..?ㅜㅜ 
				method:"get", // getmapping 
				success:function(r) { // 익명함수(function) 성공했을때 실행되어질 함수   
					//console.log(r); // 화면에 출력 (r: 응답받은 결과)
					if (r.trim() == '1'){
						$("#duplicateMsg").html("사용불가"); 
					} else {
						$("#duplicateMsg").html("사용가능");
					}
				 }
			});
		});
	});
			
</script>
</head>
<body>
<h2>회원가입</h2>
<form action="regist.do" method="post" onsubmit="return formCheck();">
<table border ="1">
	<tr>
		<td>아이디</td>
		<td>
		<input type = "text" name = "id" id ="id">
		<input type = "button" value ="중복체크" onclick = "idcheck();"><br>
		<span id ="duplicateMsg"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name ="pwd" id="pwd"></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name ="pwd2" id="pwd2"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type = "text" name = "name" id="name"></td>
	</tr>	
	<tr>
		<td>학교</td>
		<td>
			<table>
				<tr>
					<td>학교명</td>
					<td>졸업년도</td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
				<tr>
					<td><input type="text" name="school"></td>
					<td><input type="text" name="year"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="가입"></td>	
	</tr>
</table>
</form>
</body>
</html>