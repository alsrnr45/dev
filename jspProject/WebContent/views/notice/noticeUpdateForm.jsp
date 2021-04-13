<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.kh.notice.model.vo.Notice"%>
    
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
        color:white;
        width:800px;
        height:500px;
        margin:auto;
        margin-top:50px;
    }
    #updateForm>table{border:1px solid white}
    #updateForm input, #updateForm textarea{
        width:100%;
        box-sizing:border-box;
    }
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>

    <div class="outer">
        <br><h2 align="center">공지사항 수정하기</h2>
        <form id="updateForm" action="<%=contextPath %>/update.no" method="post">
            <!-- 글 작성은 항상 post방식으로 , get은 데이터가 짤릴수도 있음 -->3
            <input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
            <!-- submit값 요청시 3개의 값이 전달됨,  noticNo, title, content -->
            <table align="center">
                <tr>
                    <th width="50">제목</th>
                    <td colspan="3" width="300">
                        <input type="text" name="title"  required value="<%= n.getNoticeTitle()%>">
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colsapn="3"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="content" id="" rows="10" style="resize:none;" required><%= n.getNoticeContent() %></textarea>
                    </td>
                </tr>
            </table>

            <br><br>
            <div align="center">
                <button type="submit">수정하기</button>
                <button type="button" onclick="history.back();">뒤로가기</button>
                <!-- 공지사항 상세보기하고자 한다면 /detail.no?nno = 글번호 요청 -->
            </div>
        </form>
    </div>
</body>
</html>