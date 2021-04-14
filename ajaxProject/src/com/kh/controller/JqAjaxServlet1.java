package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxServlet1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String str = request.getParameter("input");
		System.out.println(str);
		
		// 요청 처리 다 했다는 가정하에 응답할 데이터 : 문자열
		String responseData = "입력된 값 : " + str + ", 길이 : " + str.length();
		
		
		// 응답할 데이터에 한글이 있을 경우 인코딩 작업 필요함
		response.setContentType("text/html; charset=UTF-8");
		// text를 html로 ;으로 구분, 
		
		response.getWriter().print(responseData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
