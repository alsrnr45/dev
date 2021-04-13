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
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/insert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 전달값 중에 한글값이 있을 경우 대비해서 인코딩 
		request.setCharacterEncoding("utf-8");
		
		// 2. 요청시 전달값 뽑아서 변수 및 객체에 기록
		String userId = request.getParameter("userId"); // "아이디값"
		String userPwd = request.getParameter("userPwd"); // "비밀번호값"
		String userName = request.getParameter("userName"); // "이름값"
		String phone = request.getParameter("phone"); // "전화번호값" / ""
		String email = request.getParameter("email"); // "이메일값" / ""
		String address = request.getParameter("address"); // "주소값" / ""
		String[] interestArr = request.getParameterValues("interest"); // ["", ""] / null
		// 	  String[]    -->   String
		// ["운동", "등산"] -->  "운동,등산"
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		// 기본생성자로 생성후 setter메소드 이용해서 담기 / "매개변수 생성자 이용해서 담기"
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		//System.out.println(m);
		
		// 3. 요청 처리를 위한 Service단의 메소드 호출 및 결과 받기
		int result = new MemberService().insertMember(m);
		
		// 4. 돌려받은 결과를 가지고 사용자가 보게될 응답페이지 지정 후 포워딩 또는 url재요청
		if(result > 0) { // 성공일 경우 => 메인페이지(index)
			
			// alert로 띄워주고자 하는 메세지 담기 => session에 담기
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원가입 되었습니다.");
			
			response.sendRedirect(request.getContextPath());
			
		}else { // 실패일 경우 => 에러페이지 (에러문구)
			
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
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
