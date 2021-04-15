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

                        <!-- 로그인 전 일 경우 -->
                        <%if(loginUser == null){ %>
                        <td><textarea cols="50" rows="3" style="resize:none" readonly>로그인 후 이용가능한 서비스입니다.</textarea></td>
                        <td><button disabled>댓글등록</button></td>
						<%} else { %>
                        <!-- 로그인 후 일 경우 -->
                        <td><textarea id="replyContent" cols="50" rows="3" style="resize:none" ></textarea></td>
                        <td><button onclick="addReply();">댓글등록</button></td>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                
                </tbody>
            </table>
            <br><br>
        </div>
    </div>
    
    <script>
    	
    	
    	// 게시글의 댓글리스트를 조회해오는 함수
    	$(function(){
    		selectReplyList();
    		
    		//setInterval(selectReplyList , 10000);
    		// 10초단위마다 매번 실행할 함수(주기적 실시간으로 갱신된 댓글리스트 조회요청)
    		
    		
    	})
    	
    	// 해당 게시글에 댓글 작성용 ajax
    	
    	// 요청시 회원번호는 넘기지 않을거임! 서블릿에서 작업할거임
    	function addReply(){
    		$.ajax({
    			url: "<%=contextPath%>/rinsert.bo",
    			type: "post",
    			data: {
    				content:$("#replyContent").val(), // content라는 key값으로 id가 replyContent인 값을 넘기겠다.
    				bno:<%=b.getBoardNo()%> // bno라는 key값으로 board객체에 BoardNo값을 가져오겠다.
    				<%-- userNo:<%=loginUser.getUserId()%> loginUser가 null값으로 존재할 수 있기 때문에 nullpoint익셉션 발생가능--%> 
    			},
    			success:function(result){
    				if(result > 0){
    					// 댓글 성공하면 내가 쓴 댓글이 최상단에 붙여지기 (갱신된 리스트 다시 조회해서 뿌려줘야함)
    					selectReplyList();
    					// 깔끔하게 입력했던 textarea의 내용도 비워줘야 함 => replyContent에 ""로 깔끔하게 비워줌
    					$("#replyContent").val("");
    				} else{
    					// 댓글 실패
    				}
    			},
    			error:function(){
    				console.log("댓글 작성용 ajax 통신 실패");
    			}
    		})
    	}
    	
    	// 해당  게시글에 딸린 댓글리스트 조회용 ajax
    	function selectReplyList(){
    		
    		$.ajax({
    			url: "<%=contextPath%>/rlist.bo",
    			data: {bno:<%=b.getBoardNo()%>},
    			success:function(list){
    				var result ="";
    				for (var i in list){
						result += "<tr>" 
							    + 	"<td>" + list[i].replyWriter + "</td>"
							    +   "<td>" + list[i].replyContent + "</td>"
							    +   "<td>" + list[i].createDate + "</td>"
							    + "</tr>";
					}
    				
    				$("#replyArea tbody").html(result);
    			},
    			error:function(){
    				console.log("댓글 리스트 조회용 ajax 통신 실패");
    			}
    			
    		});
    	}
    </script>
</body>
</html>