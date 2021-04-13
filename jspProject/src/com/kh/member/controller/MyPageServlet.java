package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage.me")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// url로 직접요청도 가능하기 때문에
		HttpSession session = request.getSession();
		
		// 로그인 전에 해당 url 요청됐을 경우 => 메인페이지 출력 / alert "로그인 후 이용 가능한 서비스입니다."
		if(session.getAttribute("loginUser") == null) {
			
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			
			response.sendRedirect(request.getContextPath());
			
			
		} else { // 로그인 후에 해당 url 요청 됐을 경우 => 
			
		// 이번에는 별도로 전달되는 값은 없다.
		// 단순히 응답페이지 => 마이페이지 출력해주는 역할(myPage.jsp)
		// 마이페이지 보고 싶으면 => url로 /jsp/myPage.me 요청
		// myPage.jsp 포워딩
		// 사용자에게 보여줄 응답뷰 선택
		RequestDispatcher view = request.getRequestDispatcher("views/member/myPage.jsp"); // 개인오류:404 (주소값 "views/member.myPage.jsp"로 되어있었음)
		view.forward(request, response);
		
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
