<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.notice.model.vo.Notice"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); // 객체로 return 되기 때문에 객체형변환
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background-color: black;
        color:white;
        width:800px;
        height: 500px;
        margin:auto;
        margin-top:50px;
    }

    .listArea {
        border:1px solid white;
        text-align: center;
        margin:auto;
    }

    .listArea>tbody>tr:hover{
        cursor:pointer;
        background-color: gray;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
        <br>
        <h2 align="center">공지사항</h2>
        <table class="listArea" >
        <br>
        <!-- 로그인했고, 로그인한 사용자가 admin일 경우 보여지는 div-->
        <% if(loginUser != null && loginUser.getUserId().equals("admin")){ %>
	        <div align="right" style=" width:700px">
	            <!--   <button onclick="location.href='요청할 url';">글 작성</button> -->
	            <!-- 버튼 클릭시 url요청하는 방법 2가지 a태그 or onclick이벤트 -->
	            <a href="<%=contextPath%>/enrollForm.no" class="btn btn-secondary btn-sm">글작성</a>
	            <!-- a태그로 하면 모양은 어떻게 주지? bootstrap 연결되어있으니까 class 부여해서 스타일 입히면돼!  -->
	        </div>
	    <% } %>    
        <br><br>
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="300">글제목</th>
                    <th width="100">작성자</th>
                    <th>조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>

            <tbody>
            	<!-- 순차적으로 하나하나 index의 값을 하나하나 담게하겠다 어떻게? 반복문으로
				
				방법 1.
				
				for(int i=0; i<list.size(); i++){
					list.get(i).getNoticeNo()
				}
				
				방법 2.
				
				for(Notice n : list){
					n.getNoticeNo()
				}
				
				근데 list가 비어있을 경우가 있음! 그 부분도 생각해서 따로 구현할 화면을 만들어 줘야함!
			 	-->
			 
			 <% if(list.isEmpty()) { %>
			 	<tr>
			 		<td colspan="5"> 존재하는 공지사항이 없습니다. </td>
			 	</tr>
			 <% } else { %>
			 	<% for(Notice n : list){%>
			 		<tr>
			 			<td><%= n.getNoticeNo() %></td>
			 			<td><%= n.getNoticeTitle() %></td>
			 			<td><%= n.getNoticeWriter() %></td>
			 			<td><%= n.getCount() %></td>
			 			<td><%= n.getCreateDate() %></td>
			 		</tr>
			 	<% } %>
			 <% } %>
            </tbody>
        </table>
    </div>
    
    
    <script>
    // tbody 영역 안에 있는 리스트들 클릭할 때 발생하는 이벤트 만들기
    $(function(){
    	$(".listArea>tbody>tr").click(function(){
    		// jsp/detail.no
    		location.href = '<%=contextPath%>/detail.no?nno=' + $(this).children().eq(0).text();
    		// $(this) 현재 클릭된 요소를 가리켜줌=> 모든요소 클릭됨 하지만 우리가 필요한 건 글번호뿐이므로
    		// 그 자손들 선택해주어야 함(n.getNoticeNo())를 가져오는 위 로직.
    	
    	})
    	
    })
    </script>
</body>
</html>