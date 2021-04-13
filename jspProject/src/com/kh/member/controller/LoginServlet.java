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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  < HttpServletRequest 객체와 HttpServletResponse 객체 >
		 *  - request : 서버로 요청하는 정보들이 담겨있음 (요청시 전달값, 요청한 클라이언트 ip주소 등등)
		 *  - response : 요청에 대해 응답할 때 필요한 객체
		 *  
		 *  < Get방식과 Post방식의 차이 >
		 *  - get방식 : URL의 Header에 데이터가 기록되서 전달 (url노출o/데이터길이제한o/대신 즐겨찾기 가능)
		 *  - post방식 : URL의 Body에 데이터가 기록되서 전달 (url노출x/데이터길이제한x/대신 TimeOut있음)
		 */
		
		// 1. 전달값에 한글이 있을 경우 인코딩 처리 (post방식일때만)
		request.setCharacterEncoding("utf-8");
		
		// 2. 요청시 전달값(request의 parameter영역) 꺼내서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId"); // "사용자가입력한아이디값"
		String userPwd = request.getParameter("userPwd"); // "사용자가입력한비밀번호값"
		
		// 3. 해당 요청을 처리하는 서비스 클래스의 메소드 호출 및 그 결과 받기
		Member m = new MemberService().loginMember(userId, userPwd);
		
		// 4. 처리된 결과를 가지고 사용자가 보게될 뷰 지정 후 포워딩 또는 기존의 url 리다이렉트
		/*
		 *  * 응답페이지에 전달할 값이 있을 경우 어딘가에 담아야됨!! (담아줄 수 있는 jsp내장객체 4종류)
		 *  1) application : application에 담은 데이터 웹 애플리케이션 전역(jsp, servlet, java)에서 다꺼내 쓸수있다.
		 *  2) session : session에 담은 데이터는 모든 jsp와 servlet에서 꺼내 쓸 수 있다. 
		 *  3) request : request에 담은 데이터는 오로지 응답 jsp에서만 꺼내 쓸 수 있다. 
		 *  4) page : 해당 jsp페이지에서만 꺼내 쓸 수 있다. 
		 *  
		 *  Session 객체 : 웹 브라우저당 하나씩 존재하는 객체
		 *  			   로그인한 회원 정보를 Session에 한번만 담아두시면
		 *  			   어떤 페이지(jsp)든, 어떤 servlet이든 Session에 담겨있는 
		 *  			   회원 데이터를 꺼내 쓸 수 있음!  			  
		 *  
		 *  .setAttribute("키", 밸류); 를 이용해서 데이터 담음
		 *  밸류값 꺼낼때는 .getAttribute("키") 이용
		 *  삭제하고자 할 때는 .removeAttribute("키") 이용
		 *  
		 */
		if(m == null) { // 로그인 실패 => 에러페이지
			
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			// 포워딩(forwarding) 방식 : 해당 뷰가 선택되서 보여질 뿐 여전히 현재 이서블릿 매핑값이 남아있음
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
		}else { // 로그인 성공 => 메인페이지(index)
			
			// Session객체에 접근하고자 한다면 Session 객체를 메소드를 이용해서 얻어와야됨
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", m);
			
			// 1. forwarding 방식으로 응답뷰 출력하기
			//    해당 뷰가 선택되서 보여질 뿐 url에는 여전히 현재 이 서블릿 매핑값이 남아있음
			//RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			//view.forward(request, response);
			
			// 2. sendRedirect 방식 (url 재요청 방식) => 내가 제시한 url값이 노출 
			//response.sendRedirect("/jsp");
			response.sendRedirect(request.getContextPath());
			
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
