<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    #enrollForm>table{ border:1px solid white; }
    #enrollForm input, #enrollForm textarea{
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

        <form action="<%=contextPath%>/insert.bo" id="enrollForm" method="post" enctype="multipart/form-data">
            <!-- 카테고리번호, 제목, 내용, 첨부파일, 작성자회원번호 -->
            <table align="center">
                <tr>
                    <th width="70">카테고리</th>
                    <td width="500">
                        <select name="category">
                            <option value="10">공통</option>
                            <option value="20">운동</option>
                            <option value="30">등산</option>
                            <option value="40">게임</option>
                            <option value="50">낚시</option>
                            <option value="60">요리</option>
                            <option value="70">기타</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" rows="10" style="resize:none" required></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><input type="file" name="upfile"></td>
                </tr>
            </table>
            <input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>"> <!-- loginUser의 UserNo값을 돌려받겠다 -->
            <br>
            <div align="center">
                <button type="submit">작성하기</button>
                <button type="reset">취소하기</button>
            </div>
        </form>
    </div>
</body>
</html>