package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//---------------------페이징 처리할때 필요한 변수 ----------------
		int listCount;     // 현재 총 게시글 횟수
		int currentPage;   // 현재 페이지(즉, 요청한 페이지)
		int pageLimit;     // 한 페이지 하단에 보여질 페이징바의 페이지 최대갯수(몇개단위)
		int boardLimit;    // 한 페이지 내에 보여질 게시글 최대 갯수(몇개 단위)
		
		int maxPage;       // 전체 페이지들 중 가장 마지막 페이지(listCount, boardLimit를 가지고 구할것)
		int startPage;     // 현재 페이지에 하단에 보여질 페이징 바의 시작수(currentPage, pageLimit를 가지고 구할것)
		int endPage;       // 현재 페이지에 하단에 보여질 페이징 바의 끝 수 (statPage, pageLimit, maxPage를 가지고 구할 것)
		
		// * listCount : 총 게시글 갯수 조회해서 담기
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 현재 요청한 페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// * pageLimit : 한 페이지 하단에 보여질 페이지 최대 개수(페이지 목록들 몇 개 단위)
		pageLimit = 10;
		
		// * boardLimit : 한 페이지 내에 보여질 게시글 최대 개수
		boardLimit = 10;
		
		/* maxPage : 총 페이지 수(마지막 페이지)
		 * 
		 * listCount, boardLimit에 영향을 받음
		 * ex) boardLimit이 10이라는 가정 하에...
		 * -------------------------
		 * 총 개수(listCount) : 100
		 * 한 페이지 내 보여질 게시글 최대 개수(boardLimit) : 10
		 * => maxPage : 10
		 * --------------------------
		 * 총 개수 (listCount) : 101
		 * 한 페이지 내 보여질 게시글 최대개수(boardLimit) : 10
		 * => maxPage : 11
		 * --------------------------
		 * 총 개수 (listCount) : 105
		 * 한 페이지 내 보여질 게시글 최대개수(boardLimit) : 10
		 * => maxPage : 11		  
		 * --------------------------
		 * 총 개수 (listCount) : 109
		 * 한 페이지 내 보여질 게시글 최대개수(boardLimit) : 10
		 * => maxPage : 11
		 * 
		 * listCount / boardLimit 
		 * 강제올림처리 하면 maxPage와 알맞게 배치가 됨
		 * 
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		// => ceil이 올림형태여도 정수형이 아니라 실수형이므로 정수형으로 형변환 필요함
		
		/*
		 * startPage : 현재 페이지에 보여질 페이징 바의 시작 수...
		 * pageLimit, currentPage에 영향을 받음
		 * 
		 * ex) pageLimit이 10이라는 가정하에 
		 * startPage: 1, 11, 21, 31, ...
		 * 
		 * currentPage : 1
		 * startPage : 1 
		 * --------------
		 * currentPage : 5
		 * startPage : 1
		 * ---------------
		 * currentPage : 10
		 * startPage : 1
		 * ---------------
		 * currentPage: 11
		 * startPage: 11
		 * -----------------
		 * currentPage : 15
		 * startPage: 11
		 * ------------------
		 * currentPage: 20
		 * startPage: 11
		 * 
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1 ;
		
		/* * endPage : 현재 페이지에 보여지는 페이징바의 끝 수
		 * startPage, pageLimit에 영향을 받음
		 * 
		 * ex) pageLimit이 10이라는 가정 하에
		 * 
		 * startPage : 1 => endPage : 10
		 * startPage : 11 => endPage : 20 
		 *                   endPage : startPage + pageLimit - 1
		 * 
		 */
		endPage = startPage + pageLimit - 1;
		
		// 만약, maxPage가 고작 13까지 밖에 안된다면? endPage를 13로 해줘야 됨!
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징정보들을 어딘가의 한 공간에 담자! 저거 일일이 다 쓰기 어려우니까 아예 PageInfo 객체를 만들어서 그 안에서 전달하자
		// 1. 페이징바 만들때 필요한 PageInfo객체 만들기
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage );
		
		// 2. 현재 요청한 페이지(currentPage)에 보여질 게시글 리스트 조회해오기
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		// 조회된 pi, list 객체 가져오기
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
	
		// 3. 화면에 띄워주기
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
