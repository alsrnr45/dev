package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdate
 */
@WebServlet("/update.no")
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		// 공지사항의 번호를 찾아 넘기겠다
		
		String noticeTitle = request.getParameter("title"); // title값에 담긴 것을 noticeTitle에 담겠다.
		String noticeContent = request.getParameter("content"); // content값에 담긴 것을 noticeContent에 담겠다.
		
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		// n 객체에 번호,제목,내용 담겠다.
		
		int result = new NoticeService().updateNotice(n);
		
		if(result>0) { // 수정 성공 => /detail.no?nno=글번호 url재요청 => 상세보기 페이지 
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항 수정됐습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.no?nno=" + noticeNo);
		} else { // 수정 실패 => 에러 문구 담아서 에러페이지 포워딩
			request.setAttribute("errorMsg", "공지사항 수정 실패");
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
