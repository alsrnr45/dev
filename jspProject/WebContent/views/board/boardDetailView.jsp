<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.*"%>
    
<%
	Board b = (Board)request.getAttribute("b");
	// 게시글 번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일
	
	Attachment at = (Attachment)request.getAttribute("at");
	// null 또는
	// 파일번호, 원본명, 수정명(실제서버에 업로드된 이름), 저장폴더경로
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background: black;
        color:white;
        width: 1000px;
        height: auto;
        margin: auto;
        margin-top: 50px;
    }
    .outer table{border-color: white;}
    #detailArea p{height: 200px;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세보기</h2>
        <br>

        <table id="detailArea" align="center" border="1">
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%= b.getCategoryNo() %></td>
                <th width="70">제목</th>
                <td width="350"><%= b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%= b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p><%= b.getBoardContent() %></p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                
                <% if(at == null){ %>
                	<!-- 첨부파일이 없을 경우 -->
                	첨부파일이 없습니다.
                <% } else{ %>
               		<!-- 첨부파일이 있을 경우 -->
               		<!-- download속성 넣기 => a태그안에 다운로드 속성 추가(=getOriginName 해주면 원본명으로 저장) -->
                     <a download="<%= at.getOriginName() %>" href="<%= contextPath%>/<%= at.getFilePath() + at.getChangeName() %>"><%= at.getOriginName() %></a>
                <% } %>
          
                </td>
            </tr>
        </table>

        <br><br>

        <!-- 로그인이 되어있고, 로그인한 사용자가 게시글 작성자일 경우 -->
        <% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
        
        <div align="center">
            <a href="<%= contextPath %>/updateForm.bo?bno=<%= b.getBoardNo() %>" class="btn btn-warning btn-sm">수정하기</a>
            <a href="" class="btn btn-danger btn-sm">삭제하기</a>
        </div>
        
        <% } %>
        <br>
        <div id="replyArea">
            <table border="1" align="center">
                <thead>
                    <tr>
                        <th>댓글작성</th>

                        <!-- 로그인 전 일 경우 
                        <td><textarea cols="50" rows="3" style="resize:none" readonly>로그인 후 이용가능한 서비스입니다.</textarea></td>
                        <td><button disabled>댓글등록</button></td>
						-->
						
                        <!-- 로그인 후 일 경우 -->
                        <td><textarea cols="50" rows="3" style="resize:none" ></textarea></td>
                        <td><button>댓글등록</button></td>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>admin</td>
                        <td>댓글내용</td>
                        <td>2021년 04월 08일</td>
                    </tr>
                    
                    <tr>
                        <td>admin</td>
                        <td>댓글내용</td>
                        <td>2021년 04월 08일</td>
                    </tr>

                    <tr>
                        <td>admin</td>
                        <td>댓글내용</td>
                        <td>2021년 04월 08일</td>
                    </tr>
                </tbody>
            </table>
            <br><br>

        </div>
    </div>
</body>
</html>