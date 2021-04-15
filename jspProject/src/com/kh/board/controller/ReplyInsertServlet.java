package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/rinsert.bo")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyContent = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		// login 성공시 Session에 loginUser라는 값이 담겨있음음
		// Session에 담긴 값을 getAttribute 메소드를 통해서 key값이 loginUser를 뽑아오겠다.
		// Member객체에 담겨있는 userNo값을 가져오겠다.(getUserNo)
		
		// 이 3개를 각자 보내면 힘들어서 Reply객체에 담아서 넘길게용~
		Reply r = new Reply();
		r.setReplyContent(replyContent); // Reply객체에 Content를 ReplyContent에 담기
		r.setRefBno(boardNo); // Reply객체에 boarNo 숫자를  RefBno에 담기
		r.setReplyWriter(String.valueOf(userNo)); // Reply객체에 userNo값을 문자로 ReplyWriter에 담기
		
		int result = new BoardService().insertReply(r);
		response.getWriter().print(result);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
