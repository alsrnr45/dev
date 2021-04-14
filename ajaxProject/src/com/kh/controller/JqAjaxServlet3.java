package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.model.vo.User;

/**
 * Servlet implementation class JqAjaxServlet3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원조회(조회하고자 하는 회원번호) 요청시 전달되는 값 변수에 담기
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 요청 처리를 위한 서비스 호출 -> dao에서 조회(xml에서 sql문 요청을 통해 db에서 조회된 데이터 뽑아오는 과정 해야함)
		// 지금은 생략
		// User findUser = new UserService().selectUser(no);
		// 위의 내용이 실행돼서 조회된 데이터가 담긴 User객체가 아래와 같다는 가정하에
		User findUser = new User(1, "박철수", 30, "남");
		
		//response.getWriter().print(findUser); // findUser.toString()
		// findUser 객체 전달하여 출력하기
		// findUser.toString() 이 문자열이 전달되어 출력됨.
		
		/*
		 * JSON (JavaScript Object Notation : 자바스크립트 객체 표기법)
		 * - {속성명 : 속성값, 
		 *    속성명 : 속성값,
		 *    key : value }
		 *    자바스크립트 객체 표기법임
		 *    지금은 JSON 표기법이 JAVA에서 도와주지 않아 따로 라이브러리가 있어야함
		 *    -라이브러리 필요 :  https://code.google.com/archive/p/json-simple/downloads
		 *   
		 */
		JSONObject jsonUser = new JSONObject(); // {}
		jsonUser.put("no",findUser.getNo()); // {no:1}
		jsonUser.put("name", findUser.getName()); // {no:1, name:"박철수"}
		jsonUser.put("age", findUser.getAge()); // {no:1, name:"박철수", age:30}
		jsonUser.put("gender", findUser.getGender()); // {no:1, name:"박철수", age:30, gender:"남"}
		// 이렇게 하나씩 차곡차곡 쌓아나가면 됨
		
		response.setContentType("application/json; charset=UTF-8");
		// json을 사용하려면 설정해주고 charset까지 해주면 됨
		
		response.getWriter().print(jsonUser);
		// getWriter라는 통로를 만들고 jsonUser를 출력시키기
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
