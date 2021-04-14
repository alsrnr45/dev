<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		background:black;
		color:white;
		width:800px;
		margin:auto;
		margin-top:50px;
	}
	#enrollForm table{margin:auto;}
	#enrollForm input{margin:5px;}
</style>
</head>
<body>
	<!-- 메뉴바 포함시킬것 -->
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">회원가입</h2>

		<form action="<%= request.getContextPath() %>/insert.me" method="post" id="enrollForm">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="userId" maxlength="12" required></td>
					<td><button type="button" onclick="idCheck();">중복확인</button></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="5" required></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="(-포함해서 입력)"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;관심분야</td>
					<td colspan="2">
						<input type="checkbox" name="interest" value="운동" id="sports">
						<label for="sports">운동</label>
						
						<input type="checkbox" name="interest" value="등산" id="climbing">
						<label for="climbing">등산</label>
						
						<input type="checkbox" name="interest" value="낚시" id="fishing">
						<label for="fishing">낚시</label>

						<br>
						
						<input type="checkbox" name="interest" value="요리" id="cooking">
						<label for="cooking">요리</label>
						
						<input type="checkbox" name="interest" value="게임" id="game">
						<label for="game">게임</label>
						
						<input type="checkbox" name="interest" value="기타" id="etc">
						<label for="etc">기타</label>
					</td>
				</tr>
			</table>
			<br><br>
			<div align="center">
				<button type="submit" disabled>회원가입</button>
				<button type="reset">초기화</button>
			</div>
		</form>
		<br><br>
	</div>
	<script>
		function idCheck(){
			// form 요소 안에 name이 userId인 input요소객체 선택하기
			var $userId = $("#enrollForm input[name=userId]"); // value값 아님 이후에 userId.val()로 알아내면 됨
			// 이거 jQuery 방식으로 입력한 객체인 것을 알려주기 위해 $ 붙이기
			
			$.ajax({
				url: "idCheck.me",
				type: "get",
				data: {checkId:$userId.val()},
				success: function(result){
					if(result == 'NNNNN'){ // 사용불가능(기존에 아이디가 있음)
						
						alert("이미 존재하거나 탈퇴한 회원의 아이디입니다.")
						$userId.focus();
						
					} else{ // 사용가능(아이디 생성 가능)
						if(confirm("사용가능한 아이디입니다. 정말로 사용하시겠습니까?")){
							// 사용하겠다 => 더 이상 변경불가, 회원가입버튼활성화
							$userId.attr("readonly", true);
							$("#enrollForm :submit").removeAttr("disabled");
							// id가 enrollForm인 자식요소들 중  submit 요소의 속성(disabled)을 제거하겠다
						} else{
							// 사용가능하지만 다시 입력하겠다
							$userId.focus();
						}
					}
				}, 
				error: function(){
					console.log("아이디 중복체크용  ajax 통신실패")	
				},
				
				
			});
		}
	</script>
	
	
</body>
</html>