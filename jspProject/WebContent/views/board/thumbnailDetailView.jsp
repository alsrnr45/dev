<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    .outer{
        background: black;
        color: white;
        width:1000px;
        height:800px;
        margin:auto;
        margin-top:50px;
        
        }
    .detailArea td, .detailArea th{
        border : 1px solid white;
        
    }
</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<input type="hidden" >
    <div class="outer">
        <br>
        <h2 align="center">사진게시판 상세보기</h2>
        <br>

        <table class="detailArea" align="center">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="600"><%= b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%= b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3" height="50px"><%= b.getBoardContent() %></td>
            </tr>
            <tr>
                <th>대표사진</th>
                <td colspan="3">
                    <div align="center">
                        <img src="<%=contextPath %>/<%= list.get(0).getFilePath() + list.get(0).getChangeName() %>" alt="" width="500px;" height="300px;">
                    </div>
                </td>
            </tr>
            <tr>
                <th>상세사진</th>
                <td colspan="3">
                    <div align="center">
                    <% for(int i=1; i<list.size(); i++){ %>
                        <img src="<%=contextPath %>/<%= list.get(i).getFilePath() + list.get(i).getChangeName() %>" alt="" width="200" height="150">
                        <% } %>
                    </div>
                </td>
            </tr>

        </table>
    </div>
</body>
</html>