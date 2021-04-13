package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/delete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		if(result>0) { // 탈퇴성공
			// => 세션에 담겨있는 loginUser 지워주기 => 메인페이지 alert "탈퇴되었습니다"
			
			HttpSession session = request.getSession();
			session.removeAttribute("loginUser");
			session.setAttribute("alertMsg", "탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
			
			response.sendRedirect(request.getContextPath());
			
			
		} else { // 탈퇴실패 -> 에러페이지
			request.setAttribute("errorMsg", "회원 탈퇴 실패");
			//RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			//view.forward(request, response);
			// == 이렇게 두 줄을 한 줄로도 줄여짐 
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
