package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.User;

/**
 * Servlet implementation class JqAjaxServlet4
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxServlet4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ArrayList<User> list = new UserService().selectList();
		// 원래는 저 서비스로 넘기는 과정이지만, 지금은 되었다는 전제하에 한번 해봅시다
		
		ArrayList<User> list = new ArrayList<>();
		list.add(new User(1, "박철수", 30, "남")); // JSONObject {}
		list.add(new User(2, "박철산", 35, "남")); // JSONObject {}
		list.add(new User(3, "박수홍", 25, "남")); // JSONObject {}
		list.add(new User(4, "박수철", 42, "남")); // JSONObject {}
		list.add(new User(5, "박찬수", 46, "남")); // JSONObject {}
		
		// JSONArray [{},{},{}] js에서의 객체배열 표현
		
		// response.getWriter().print(list/*.toString()*/); toString으로 출력해줌
		
		/* JSONArray jArr = new JSONArray(); // [] 빈 배열 생성
		// 객체들을 하나하나 배열에 담을거니까 반복문 쓸겡
		for(User u : list) {
			JSONObject jObj = new JSONObject(); // JSON 객체 생성 { }
			jObj.put("no",u.getNo()); // {no:x}
			jObj.put("name", u.getName()); // {no:x, name:"xxx"}
			jObj.put("age", u.getAge()); // {no:x, name:"xxx", age:xx}
			jObj.put("gender", u.getGender());//  {no:x, name:"xxx", age:xx, gender:x}
			
			jArr.add(jObj);
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jArr);
		
		*/
		
		//GSON (Google JSON) 이용하면 보다 쉽게 응답할 수 있음!!
		// http://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.2
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter()); // 응답할 객체, 응답할 스트림 제시해야 함
		
		// 지금 이 단 2줄 코드로 위 과정을 다 끝내버림
		// 위에서는 직접 key값을 내가 비교했는데 그럼 Gson은 어떻게 key값을 구성했을까?
		// 내부적으로 Json객체로 변환시 그 때의 key값은 User클래스의 필드명으로 세팅!
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
