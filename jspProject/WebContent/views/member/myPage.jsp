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
	.modal-content {
		color:black;
	}
	#myPageForm table{margin:auto;}
	#myPageForm input{margin:5px;}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	<!-- include된 jsp 내에 선언된 변수 쓸 수 있다. (loginUser 쓸 수 있다!) -->
	
	<%
		String userId = loginUser.getUserId();
		String userName = loginUser.getUserName();
		// 필수입력사항이 아닌 부분은 null일 경우 빈 문자열 출력되도록 삼항연산자 이용하기
		String phone = (loginUser.getPhone() == null) ? "" : loginUser.getPhone();
		String email = (loginUser.getEmail() == null) ? "" : loginUser.getEmail();
		String address = (loginUser.getAddress() == null) ? "" : loginUser.getAddress();
		String interest = (loginUser.getInterest() == null) ? "" : loginUser.getInterest();
	%>
	
    <div class="outer">
		<br>
		<h2 align="center">마이페이지</h2>

		<form action="<%= request.getContextPath() %>/update.me" method="post" id="myPageForm">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="userId" maxlength="12" required value="<%= userId %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="5" required value="<%= userName %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="(-포함해서 입력)" value="<%= phone %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email" value="<%= email %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address" value="<%= address %>"></td>
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
			
			<script>
				$(function(){
					var interest = "<%=interest%>";
					// "운동, 낚시, 게임..." or "빈 문자열"
					
					// 체크박스인 input요소들에 순차적으로 접근하면서 해당 그 input요소의 value값이 interest에 포함되어 있을 경우
					// => 해당 input요소에 checked속성 부여
					
					// 인풋이 체크박스 타입인 요소 하나하나가 "만약" 순차적으로 접근할 때의 인터레스트의 값의 인덱스를 하나하나 뽑겠다
					$("input[type=checkbox]").each(function(){
						if(interest.search($(this).val()) != -1){
							$(this).attr("checked", true);
						}
					})
					
				})
				
			</script>
			<br><br>
			<div align="center">
				<button type="submit" class="btn btn-primary">정보변경</button>
				<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updatePwdModal">비밀번호변경</button>
                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal">회원탈퇴</button>
            </div>
		</form>

		<br><br>
	</div>
	
	<!-- 비밀번호 변경 버튼 클릭시 뜨는 Modal -->
			
	<!-- The Modal -->
		<div class="modal" id="updatePwdModal">
			<div class="modal-dialog">
				<div class="modal-content">
				
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">비밀번호변경</h4>
				    <button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
					<div class="modal-body" align="center">
				        <b>
				        	비밀번호를 변경하시겠습니까? <br><br>
				        </b>
					     <form action="<%= request.getContextPath() %>/updatePwd.me" method="post">
							<input type="hidden" name="userId" value="<%=userId%>">
						<table>
							<tr>
								<th>현재 비밀번호</th>
								<td><input type="password" name="userPwd" required></td>
							</tr>
							<tr>
								<th>변경할 비밀번호</th>
								<td><input type="password" name="updatePwd" required></td>
							</tr>
							<tr>
								<th>변경할 비밀번호 재입력</th>
								<td><input type="password" name="checkPwd" required></td>
							</tr>
						</table>
 	
						     <button type ="submit" class="btn btn-outline-warning btn-sm" onclick="return validatePwd();">비밀번호변경하기</button>
						     <!-- 무조건 조건 검사후 제출될 수 있도록 클릭에다가 함수를 넣어줘야 함 -->
						     <script>
						     	function validatePwd(){
						     		if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()){
						     			alert("변경할 비밀번호가 일치하지 않습니다.");
						     			return false;
						     			// 사용자가 입력한 updatePwd != checkPwd 이면 실행 안되게, 
						     		};
						     	}
						     </script>
						 </form>
					</div>
				  </div>
				</div>
			</div>
	
	<!-- 회원탈퇴버튼 클릭시 뜨는 Modal -->
			
	<!-- The Modal -->
		<div class="modal" id="deleteModal">
			<div class="modal-dialog">
				<div class="modal-content">
				
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원탈퇴</h4>
				    <button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<!-- Modal body -->
					<div class="modal-body" align="center">
				        <b>
				        	탈퇴 후 복구가 불가능합니다. <br>
				        	정말로 탈퇴하시겠습니까? <br>
				        </b>
					     <form action="<%= request.getContextPath() %>/delete.me" method="post">
						         비밀번호 : 
						     <input type="password" name="userName" required><br><br>
						     <input type="hidden" name="userId" value="<%=userId%>"> <br>
						        	
						     <button type ="submit" class="btn btn-outline-danger btn-sm">탈퇴하기</button>
						 </form>
					</div>
				  </div>
				</div>
			</div>


	

	
	
	
</body>
</html>