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
        width: 1000px;
        height: 700px;
        margin:auto;
        margin-top: 50px;
    }

    #enrollForm table{border:1px solid white;}
    #enrollForm input, #enrollForm textarea{
        width: 100%;
        box-sizing: border-box;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="outer">
        <br>
        <h2 align="center">사진게시판 작성하기</h2>

        <form action="<%=contextPath %>/insert.th" id="enrollForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
            <table align="center">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3"><input type="text" name="title" id=""></td>
                </tr>

                <tr>
                    <th width="100">내용</th>
                    <td colspan="3">
                        <textarea rows="5" name="content" style="resize:none" required></textarea>
                    </td>
                </tr>

                <tr>
                    <th width="100">대표이미지</th>
                    <td colspan="3" align="center">
                        <!-- 대표이미지 미리보기할 img -->
                        <img id="titleImg" width="250" height="170">
                    </td>
                </tr>

                <tr>
                    <th>상세이미지</th>
                    <!-- 상세이미지를 미리보기 할 img -->
                    <td><img id="contentImg1" width="250" height="170"></td>
                    <td><img id="contentImg2" width="250" height="170"></td>
                    <td><img id="contentImg3" width="250" height="170"></td>
                </tr>
            </table>
            
            <div class="fileArea">
                <!-- file1은 대표이미지이므로, 무조건 선택해서 이미지 넣을 수 있게끔 required 속성 부여하기
                밑에 파일선택 버튼이 아닌, 이미지넣을 수 있는 지역을 클릭할 때, 파일선택이 나올 수 있도록 이벤트 주기
                -->
                <input type="file" name="file1" id="file1" onchange="loadImg(this, 1);" required>
                <input type="file" name="file2" id="file2" onchange="loadImg(this, 2);">
                <input type="file" name="file3" id="file3" onchange="loadImg(this, 3);">
                <input type="file" name="file4" id="file4" onchange="loadImg(this, 4);">
            </div>
            
            <script>

                // 타이틀이미지, 상세이미지 클릭시 실행할 함수 정의
                $(function(){
                    $(".fileArea").hide();

                    $("#titleImg").click(function(){
                        $("#file1").click(); // id가 file1인 친구를 클릭할 때 발생할 이벤트 => 파일선택이벤트가 실행됨! 
                    })

                    $("#contentImg1").click(function(){
                        $("#file2").click(); // id가 file2인 친구를 클릭할 때 발생할 이벤트
                    })

                    $("#contentImg2").click(function(){
                        $("#file3").click(); // id가 file3인 친구를 클릭할 때 발생할 이벤트
                    })

                    $("#contentImg3").click(function(){
                        $("#file4").click(); // id가 file4인 친구를 클릭할 때 발생할 이벤트
                    })
                })


                function loadImg(inputFile, num){
                    // inputFile: 현재 변화가 생긴 input type = "file" 요소객체
                    // num : 몇번째 input요소인지 확인 후 해당 그 영역에 미리보기 하기 위해 

                    // 파일을 선택하는 순간, inputFile.files(속성배열) 라는 속성배열에 0번 인덱스에 파일 담김
                    if(inputFile.files.length == 1){ // 선택된 파일이 있을 경우

                        // 파일을 읽어들일 FileReader 객체 생성
                        var reader = new FileReader();

                        // 선택된 파일을 읽어들이기
                        // => 읽어들이는 순간 해당 그 파일만의 고유한 url 부여됨
                        reader.readAsDataURL(inputFile.files[0]);

                        // 파일 읽어들이기가 다 완료된 순간 실행할 함수 정의
                        reader.onload = function(e){
                            // 각 영역에 맞춰서 이미지 미리보기
                            switch(num){
                                case 1: $("#titleImg").attr("src", e.target.result); break;
                                case 2: $("#contentImg1").attr("src", e.target.result); break;
                                case 3: $("#contentImg2").attr("src", e.target.result); break;
                                case 4: $("#contentImg3").attr("src", e.target.result); break;
                                
                            }
                        }
                    } else{ // 선택된 파일이 사라졌을 경우
                        switch(num){
                                case 1: $("#titleImg").attr("src", null); break;
                                case 2: $("#contentImg1").attr("src", null); break;
                                case 3: $("#contentImg2").attr("src", null); break;
                                case 4: $("#contentImg3").attr("src", null); break;
                                
                            }
                    }
                }
            </script>

            <br>
            <div align="center">
                <button type="submit">등록하기</button>
            </div>
        </form>
    </div>
</body>
</html>