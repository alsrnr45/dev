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
        color: white;
        width:1000px;
        height: 550px;
        margin:auto;
        margin-top: 50px;
    }

    #updateForm>table{ border:1px solid white; }
    #updateForm input, #updateForm textarea{
        width:100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">일반게시판 작성하기</h2>
        <br>

        <form action="<%= contextPath %>/update.bo" id="updateForm" method="post" enctype="multipart/form-data">
            <!-- 카테고리번호, 제목, 내용, 첨부파일, 작성자회원번호 -->
            <input type="hidden" name="bno" value="<%=b.getBoardNo() %>">
            <table align="center">
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="categoryNo">
                            <option value="10">공통</option>
                            <option value="20">운동</option>
                            <option value="30">등산</option>
                            <option value="40">게임</option>
                            <option value="50">낚시</option>
                            <option value="60">요리</option>
                            <option value="70">기타</option>
                        </select>
                        <script>
                        $(function(){
                        	// option요소들에 순차적으로 접근하면서 그 때의 text값들을 해당 이 게시글의 카테고리명과 비교
                        	// 일치할 경우 그 때의 option요소에 selected 속성 부여
                        	$("#updateForm option").each(function(){
                        		if($(this).text() == "<%= b.getCategoryNo() %>"){
                        			$(this).attr("selected", true);
                        		} // 현재 내가 가리키고 있는 option에 접근한다 = $(this).text()
                        	})
                        })
                        </script>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" value="<%= b.getBoardTitle() %>" required>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" rows="10" style="resize:none" value="<%= b.getBoardContent() %>" required></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <!-- 기존의 첨부파일이 있었다면 -->
                    <td>
	                    <% if(at != null){ %>
		                    <%= at.getOriginName() %> <!-- 수정 전, 기존에 올라가 있던 파일이름 -->
		                    <input type="hidden" name="originFileNo" value="<%=at.getFileNo()%>">
		                    <input type="hidden" name="originFileName" value="<%=at.getChangeName() %>">
	                    <% } %>
	                    
	                    	<input type="file" name="reUpfile">	
                    </td>
                </tr>
            </table>
            <br>
            <div align="center">
                <button type="submit">수정하기</button>
                <button type="reset">취소하기</button>
            </div>
        </form>
    </div>
</body>
</html>