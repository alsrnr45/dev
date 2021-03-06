package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.th")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//  ServletFileUpload.isMultipartContent(request) 이것이 true일 때 실행
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1_1. 용량 제한
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 저장할 폴더의 물리적인 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			// 2. request => multipartRequest
			// 이 코드 하나로 내가 지정한 폴더에 파일들이 업로드 될 것임!!! 
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3.DB에 기록할 요청시 전달된 값 뽑기
			// 3_1. Board테이블에 insert할 데이터 뽑기
			Board b = new Board();
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
			// 3_2. Attachment테이블에 insert할 데이터뽑기 => Attachment객체
			// 단, 여러개의  첨부파일이 있을것이기 때문에 해당 Attachment객체들을 ArrayList에 담기
			
			ArrayList<Attachment> list = new ArrayList<>();
			
			for(int i=1; i<=4; i++) {
				String key ="file" + i;
				if(multiRequest.getOriginalFileName(key) != null) {
					// Attachment 객체 생성 + 원본명, 수정명, 폴더경로, 파일레벨(0/1)
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/thumbnail_upfiles/");
					
					if(i == 1) { // file1일 경우 대표이미지이다, 아닐경우는 상세이미지
						at.setFileLevel(0);
					} else {
						at.setFileLevel(1);
					}
					
					// 각 객체생성을 차곡차곡 list에 추가하기
					list.add(at);
					
				}
			}
			
			// 4. 사진게시판 작성용 서비스 호출 및 결과받기
			int result = new BoardService().insertThBoard(b, list);
			
			if(result > 0 ) { // 성공 => /list.th url 재요청 => 사진게시판 리스트페이지
				
				request.getSession().setAttribute("alertMsg", "사진 게시판 등록 성공");
				response.sendRedirect(request.getContextPath() + "/list.th");
				
			} else { // 실패 => 에러문구 담아서 에러페이지 포워딩
				
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
