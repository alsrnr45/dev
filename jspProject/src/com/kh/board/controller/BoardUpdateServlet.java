package com.kh.board.controller;

import java.io.File;
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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			 
			// 1_1. 전송되는 파일 용량 제한(int maxSize)
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 전송되는 파일을 저장시킬 서버의 폴더 물리적인 경로(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
//			// 2. HttpServletRequest request => MultipartRequest multiRequest
			MultipartRequest multiRequest  = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3. 본격적인 sql문 실행하기 위해 요청시 전달된 값 뽑기
			// 3_1. 첨부파일과는 무관하게 Board 테이블에 Update할 값 뽑기
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String categoryNo = multiRequest.getParameter("categoryNo");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategoryNo(categoryNo);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			// 3_2. 첨부파일 관련해서 Attachment테이블에  Update또는 Insert할 값 뽑기
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("reUpfile") != null) { 
				// 새로이 넘어온 첨부파일이 존재할 경우
				at = new Attachment();
				// 원본명, 수정명(실제 서버에 업로드된 파일명), 저장된폴더경로
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile")); // 원본 파일명 세팅하기
				at.setChangeName(multiRequest.getFilesystemName("reUpfile")); // 수정된 파일명 세팅하기
				at.setFilePath("resources/board_upfiles/"); // 저장된 폴더경로 세팅하기
				// 위의 3개는 기존의 첨부파일이 있었든 없었든 간에 무조건 db에 기록할 값 
				
				if(multiRequest.getParameter("originFileNo") != null) {
					// 새로운 첨부파일이 있고, 기존의 첨부파일이 있었을 경우
					// => Attachment Update 
					//    + 기존의 첨부파일 고유번호
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					// 서버에 업로드 되어있던 기존의 첨부파일 찾아서 폴더에서 삭제
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
				} else {
					// 새로운 첨부파일이 있고, 기존의 첨부파일이 없었을 경우
					// => Attachment Insert 
					//     + 수정하고 있는 게시글 번호
					
					at.setRefBoardNo(boardNo);
					// 현재 해당해있는 게시글번호 세팅
				}
				
				
		}
			
		int result = new BoardService().updateBoard(b, at);
		// case 1 : 새로운 첨부파일 x => updateBoard(b, null) 						 => Board Update
		// case 2 : 새로운 첨부파일 o, 기존의 첨부파일 o => updateBoard(b, fileNo이 담긴 at객체) => Board Update + Attachment Update
		// case 3 : 새로운 첨부파일 o, 기존의 첨부파일 x => updateBoard(b, refBoard담긴 at객체) => Board Update + Attachment Insert
		if(result > 0) {// 성공 => /detail.bo?bno=해당글번호   url재요청 => 해당게시글 상세조회 페이지
			request.getSession().setAttribute("alertMsg", "게시글 수정 성공했습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.bo?bno=" + boardNo);
		
		} else { // 싪패 => 에러문구 담아서 여러페이지 포워딩
			request.setAttribute("errorMsg", "게시글 수정 실패");
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
