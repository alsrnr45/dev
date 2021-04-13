<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.notice.model.vo.Notice"%>
<% 
	Notice n = (Notice)request.getAttribute("n");
	// 공지사항번호, 제목, 내용, 작성자아이디, 작성일 
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
        width:800px;
        height:500px;
        margin:auto;
        margin-top:50px;
    }
    #detailArea{border:1px solid white;}
    #detailArea p{height:150px;}
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp"  %>
	
	<div class="outer">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>

        <table id="detailArea" align="center" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3"><%= n.getNoticeTitle() %></td>
                <!--  notice객체에 담겨있는 제목 뽑아오겠다 -->
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= n.getNoticeWriter() %></td>
                <th>작성일</th>
                <td><%= n.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p><%= n.getNoticeContent() %></p>
                </td>
            </tr>
        </table>
        <br><br>

        <div align="center">
        	<!-- 모든 사용자 보여지기 가능 -->
            <a href="<%= contextPath %>/list.no" class="btn btn-secondary btn-sm">목록가기</a>

            <!-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우만 해당 -->
            <% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())){ %>
            <!-- loginUser null 이거나 userId=noticeWriter랑 같을 경우에만 수정,삭제 가능 -->
            <a href="<%=contextPath%>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="btn btn-secondary btn-sm">수정하기</a>
            <!-- updateForm으로 요청해서  url보면 nno라는 key값으로 공지사항글번호 잘 넘어오는지 확인-->
            <a href="<%=contextPath%>/delete.no?nno=<%= n.getNoticeNo() %>" class="btn btn-secondary btn-sm">삭제하기</a>
            <% } %>
        </div>
    </div>
</body>
</html>