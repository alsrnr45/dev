<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.board.model.vo.Board"%>
    
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    // list 객체 request에 담기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background:black;
        color: white;
        width:1000px;
        height: 800px;
        margin:auto;
        margin-top:50px;
    }

    .listArea{
        width:760px;
        margin:auto;
    }

    .thumbnail{
        border:1px solid white;
        width:220px;
        display: inline-block;
        margin:14px;
    }

    .thumbnail:hover{
        cursor:pointer;
        opacity:0.7;
    }

</style>

</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
		
    <div class="outer">

        <br>
        <h2 align="center">사진 게시판</h2>
        <br>

        <!-- 로그인한 사용자만 볼 수 있는 버튼 -->
        <% if(loginUser != null ){ %>
        <div align="right" style="width:850px;">
            <a href="<%=contextPath%>/enrollForm.th" class="btn btn-secondary btn-sm">글 작성</a>
            <br><br>
        </div>
        <% } %>

        <div class="listArea">
            
            <% for(Board b : list){ %>
            <div class="thumbnail" align="center">
            
            	<input type="hidden" value="<%= b.getBoardNo() %>">
            	
                <img src="<%= contextPath %>/<%= b.getTitleImg() %>" alt="" width="200" height="150">
                <p>
                    No.<%= b.getBoardNo() %> 게시글 제목 : <%= b.getBoardTitle() %> <br>
                   	조회수 : <%= b.getCount() %>
                </p>
            </div>
            <% } %>
			
        </div>
		<script>
			$(function(){
				$(".thumbnail").click(function(){
					location.href = '<%=contextPath%>/detail.th?bno=' + $(this).children().eq(0).val()
					// bno = 게시글번호를 받아와야하는 방법은 input hidden요소를 이용해서 하나의 b.getBoardNo()의 값을 받아와야 함 
					// 지금 선택한 요소 값 지정 => $(this) 그 요소의 자손 중 첫번째 자손의 val(값)을 호출하겠다.
				})
			})
		
		</script>
    </div>
</body>
</html>