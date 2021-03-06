<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.common.model.vo.PageInfo" %>    
    
    
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        width:1000px;
        height:550px;
        background: black;
        color:white;
        margin:auto;
        margin-top: 50px;
    }

    .listArea{
        border:1px solid white;
        text-align: center;
    }

    .listArea>tbody>tr:hover{
        background: gray;
        cursor: pointer;
    }


</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">일반게시판</h2>
        <br>

        <!-- 로그인했을때만 보여지는 div -->
        <% if(loginUser != null) {%>
        <div align="right" style="width:850px">
           <a href="<%=contextPath%>/enrollForm.bo" class="btn btn-secondary btn-sm">글작성</a> 
           <br><br>
        </div>
        <% } %>
        <table align="center" class="listArea">
            <thead>
                <tr>
                    <th width="70">글번호</th>
                    <th width="70">카테고리</th>
                    <th width="300">제목</th>
                    <th width="100">작성자</th>
                    <th width="60">조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 조회된 결과가 없을 경우 -->
                <% if(list.isEmpty()) { %>
                    <tr>
                    <td colspan="6">조회된 리스트가 없습니다.</td>
                </tr>
                <% } else { %>
                <!-- 조회된 결과가 있을 경우 -->
	            	<% for(Board b : list){ %>    
		                <tr>
		                    <td><%= b.getBoardNo() %></td>
		                    <td><%= b.getCategoryNo() %></td>
		                    <td><%= b.getBoardTitle() %></td>
		                    <td><%= b.getBoardWriter()%></td>
		                    <td><%= b.getCount() %></td>
		                    <td><%= b.getCreateDate() %></td>
		                </tr>
	                <% } %>
	         	<% } %>
             </tbody>

        </table>
		
		<script>
			$(function(){
				$(".listArea>tbody>tr").click(function(){
					// /jsp/detail.bo?bno=글번호
					
					location.href = '<%=contextPath%>/detail.bo?bno=' +$(this).children().eq(0).text() ; 
					
				})
			})
		</script>
		
        <br><br>

        <div align="center" class="pagingArea">
			
			<% if(currentPage != 1) { %>        
        	<button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage-1%>';">이전</button>
        	<% } %>
        	
            <% for(int p=startPage; p<=endPage; p++){ %>
            
	            <% if(currentPage == p){ %>
	            	<button disabled><%= p %></button>
	            <% } else { %>
	            	<button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=p%>';"><%=p%></button>
	            <% } %>
	        <% } %>
			<% if(currentPage != maxPage){ %>	        
	        <button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=currentPage+1%>';">다음</button>
	        <% } %>
		</div>

    </div>
</body>
</html>