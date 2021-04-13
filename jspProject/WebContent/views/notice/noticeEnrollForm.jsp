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
        color:white;
        width:800px;
        height:500px;
        margin:auto;
        margin-top:50px;
    }
    #enrollForm>table{border:1px solid white}
    #enrollForm input, #enrollForm textarea{
        width:100%;
        box-sizing:border-box;
    }
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>

    <div class="outer">
        <br><h2 align="center">공지사항 작성하기</h2>
        <form id="enrollForm" action="<%=contextPath %>/insert.no" method="post">
            <!-- 글 작성은 항상 post방식으로 , get은 데이터가 짤릴수도 있음 -->
            <table align="center">
                <tr>
                    <th width="50">제목</th>
                    <td colspan="3" width="300">
                        <input type="text" name="title"  required>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colsapn="3"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="content" id="" rows="10" style="resize:none;" required></textarea>
                    </td>
                </tr>
            </table>

            <br><br>
            <div align="center">
                <button type="submit">등록하기</button>
                <button type="reset">초기화</button>
            </div>
        </form>
    </div>
</body>
</html>