package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInserServlet
 */
@WebServlet("/insert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		/*
		 * 폼 전송을 일반 방식이 아닌 multipart/form-data 로 전송하는 경우 request로부터 값을 뽑을 수 없음!!
		String category = request.getParameter("category");
		String boardTitle = request.getParameter("title");
		*/
		
		
		// enctype이 multipart/form-data로 잘 전송되었을 경우
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드를 위한 외부 라이브러리 : cos.jar (com.oreilly.servlet의 약자)
			//							 http://www.servlets.com
			
			
			// 1. 전송되는 파일을 처리할 작업 내용(전송되는 파일의 용량제한, 전달된 파일을 저장할 폴더경로)
			// 1_1. 전달되는 파일의 용량제한(int maxSize => byte단위) : 10MByte로 제한
			int maxSize = 10*1024*1024; // 1MByte = 1024*1024 Byte
			
			// 1_2. 전달된 파일을 저장할 서버의 폴더 경로 알아내기 (String savePath) 
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			// 경로 작성법 : 처음에 / => 해당폴더를 선택(현재 WebContent 폴더)
			
			
			// 2. 전달된 파일명 수정 후 서버에 업로드 작업(MultipartRequest 객체 생성)
			/*
			 * HttpServletRequest request => MultipartRequest multiRequest 변환 
			 * 
			 * MultipartRequest multiRequest = new MultipartRequest(request, 저장할 폴더경로, 용량제한값, 인코딩값, 파일명수정시켜주는 객체);
			 * 
			 * 위의 구문 한 줄 실행시 MultipartRequest 객체 생성과 동시에 넘어온 첨부파일이 해당 폴더에 무조건 업로드됨!
			 * 
			 * 원본파일명을 그대로 해당 폴더에 업로드 하지 않는게 일반적임!!
			 * - 같은 파일명이 있을 경우 덮어씌워질 수 있고, 한글/특수문자/띄어쓰기가 포함된 파일명일 경우 서버에 따라 문제가 발생됨.
			 * 
			 * 기본적으로 수정명 작업을 해주는 객체 (DefaultFileRenamePolicy 객체 (cos 라이브러리))
			 * => 내부적으로 해당 객체 내의 rename() 메소드가 실행되면서 파일명 수정 진행됨!!
			 * => 기존에 동일한 파일명이 존재할 경우 뒤에 카운팅 된 숫자를 붙여줌
			 *  ex) aaa.jpg, aaa1.jpg .... 
			 *  
			 *  하지만 우리 입맛대로 절대 안겹치게끔 rename 해볼것
			 *  나만의 com.kh.common.MyFileRenamePolicy 클래스 만들어서 rename 메소드 재정의 할 것임!!
			 * 
			 * 
			 */
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// board_upfiles 폴더에 이름 수정해서 담겨있음(원본명을 따로 나중에 쓸 일이 있다면 DB에 원본명을 보관할 컬럼을 따로 기술하면 됨)
			
			// 이제 뽑은 파일을 DB에 기록할 정보들 하나하나 정리해서 담기
			
			// 3. 요청시 전달된 값들 뽑아서 vo에 주섬주섬 담기(DB에 기록할 데이터 뽑기)
			// 3_1. Board테이블 insert할 카테고리번호, 게시판제목, 게시판내용, 작성자회원번호를 Board객체에 담기
			
			String category = multiRequest.getParameter("category"); // "10"
			String boardTitle = multiRequest.getParameter("title"); // 
			String boardContent = multiRequest.getParameter("content"); 
			String boardWriter = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategoryNo(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(boardWriter);
			
			// 3_2. 첨부파일이 있다면 Attachment테이블에 Insert할 원본명, 수정명, 저장폴더경로를 Attachment객체에 담기
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) { 
				// 넘어온 첨부파일이 존재할 경우
				at = new Attachment();
				// 원본명, 수정명(실제 서버에 업로드된 파일명), 저장된폴더경로
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); // 원본 파일명 세팅하기
				at.setChangeName(multiRequest.getFilesystemName("upfile")); // 수정된 파일명 세팅하기
				at.setFilePath("resources/board_upfiles/"); // 저장된 폴더경로 세팅하기
			}
			
			// 4. 게시판 작성용 서비스 요청 및 결과 받기
			int result = new BoardService().insertBoard(b, at);
			// case1 : 첨부파일 있었을 경우 => insertBoard(생성된 Board객체, 생성된 Attachment)
			// case2 : 첨부파일이 없었을 경우 => insertBoard(생성된 Board객체, null);
			
			if(result > 0) { // 성공 
				// => 다시 리스트페이지 보여지게(=>list.bo?currentPage=1 라는 url로 요청했을 때 => 게시판리스트페이지)
				
				request.getSession().setAttribute("alertMsg", "게시글 등록 성공!!");
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1");
				// url 재요청하는 메소드 sendRedirect
				
			} else { // 실패
				request.setAttribute("errorMsg", "게시글 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
