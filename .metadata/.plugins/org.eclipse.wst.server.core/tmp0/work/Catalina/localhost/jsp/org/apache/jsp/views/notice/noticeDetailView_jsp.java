/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.64
 * Generated at: 2021-04-12 01:22:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.notice.model.vo.Notice;
import com.kh.member.model.vo.Member;

public final class noticeDetailView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/notice/../common/menubar.jsp", Long.valueOf(1617680801970L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.kh.notice.model.vo.Notice");
    _jspx_imports_classes.add("com.kh.member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
 
	Notice n = (Notice)request.getAttribute("n");
	// 공지사항번호, 제목, 내용, 작성자아이디, 작성일 

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("    .outer{\r\n");
      out.write("        background: black;\r\n");
      out.write("        color: white;\r\n");
      out.write("        width:800px;\r\n");
      out.write("        height:500px;\r\n");
      out.write("        margin:auto;\r\n");
      out.write("        margin-top:50px;\r\n");
      out.write("    }\r\n");
      out.write("    #detailArea{border:1px solid white;}\r\n");
      out.write("    #detailArea p{height:150px;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");

	// Member 타입 변수에 session의 loginUser 담기
	Member loginUser = (Member)session.getAttribute("loginUser");
	// > 로그인 전 menubar.jsp 로딩시 : loginUser => null
	// > 로그인 성공 후 menubar.jsp 로딩시 : loginUser => 로그인한 회원의 정보들이 담겨있는 객체
	// > 이 페이지를 include하는 곳은 이 loginUser라는 멤버객체를 마음껏 쓸 수있다!
	String contextPath = request.getContextPath(); 


      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("    #loginForm, #userInfo{float:right;}\r\n");
      out.write("    #userInfo a{color:black; text-decoration:none; font-size:12px;}\r\n");
      out.write("\r\n");
      out.write("    .navWrap{background-color:black;}\r\n");
      out.write("    .menu{\r\n");
      out.write("        display:table-cell;\r\n");
      out.write("        height:50px;\r\n");
      out.write("        width:150px;\r\n");
      out.write("        }\r\n");
      out.write("    .menu a{\r\n");
      out.write("        text-decoration:none;\r\n");
      out.write("        color:white;\r\n");
      out.write("        font-size:20px;\r\n");
      out.write("        line-height:50px;\r\n");
      out.write("        font-weight:bold;\r\n");
      out.write("        display:block;\r\n");
      out.write("        width:100%;\r\n");
      out.write("        height:100%;\r\n");
      out.write("    }\r\n");
      out.write("    .menu a:hover{background:darkgray}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<!-- Latest compiled and minified CSS -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery library -->\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Popper JS -->\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Latest compiled JavaScript -->\r\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tvar msg = \"");
      out.print( session.getAttribute("alertMsg") );
      out.write("\"; // 알람창으로 출력할 메세지\r\n");
      out.write("\t\t// var msg = \"메세지\"   / \"null\";\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(msg != \"null\"){\r\n");
      out.write("\t\t\talert(msg);\r\n");
      out.write("\t\t\t// session에 한 번 담아둔 건 내가 지우기 전 까지 계속 남아있다 (메뉴바 포함된 매 페이지가 열릴때마다 alert 계속 뜰 것)\r\n");
      out.write("\t\t\t// 알람창 띄워준 후에 session에 담긴 메세지 지워야함!!\r\n");
      out.write("\t\t\t");
 session.removeAttribute("alertMsg");
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t<h1 align=\"center\">Welcome JSP World</h1>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"loginArea\">\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- loginUser 값의 null 유무에 따라 로그인전, 로그인 후 화면 정하기 -->\r\n");
      out.write("\t\t");
 if(loginUser == null){ 
      out.write("\r\n");
      out.write("        <!-- 1. 로그인 전에 보여지는 로그인 form -->\r\n");
      out.write("        <!-- 이제부터는 \"/jsp\" 대신 request.getContextPath() 로  작성-->\r\n");
      out.write("        <!-- method=\"post\" : 입력한 값 url에 노출x -->        \r\n");
      out.write("        <form action=\"");
      out.print( contextPath );
      out.write("/login.me\" id=\"loginForm\" method=\"post\">\r\n");
      out.write("            <table>\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th><label for=\"userId\">아이디 : </label></th>\r\n");
      out.write("                    <td><input type=\"text\" name=\"userId\" id=\"userId\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th><label for=\"userPwd\">비밀번호 : </label></th>\r\n");
      out.write("                    <td><input type=\"password\" name=\"userPwd\" id=\"userPwd\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th colspan=\"2\">\r\n");
      out.write("                        <button type=\"submit\">로그인</button>\r\n");
      out.write("                        <button type=\"button\" onclick=\"enrollPage();\">회원가입</button>\r\n");
      out.write("                    </th>\r\n");
      out.write("                    <script>\r\n");
      out.write("                    \tfunction enrollPage(){\r\n");
      out.write("                    \t\t//loacation.href=\"/jsp/views/member/memberEnrollForm.jsp\";\r\n");
      out.write("                    \t\t// 웹 애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 위험\r\n");
      out.write("                    \t\t\r\n");
      out.write("                    \t\t// 단순한 정적인 페이지 이동이라고 해도 반드시 servlet요청후 forwarding 방식으로 응답\r\n");
      out.write("                    \t\t// => url에 servlet매핑값만 노출된다!\r\n");
      out.write("                    \t\tlocation.href = \"");
      out.print( request.getContextPath() );
      out.write("/enrollForm.me\";\r\n");
      out.write("                    \t}\r\n");
      out.write("                    </script>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table>   \r\n");
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("        \r\n");
      out.write("\t\t");
 }else { 
      out.write("\r\n");
      out.write("        <!-- 2. 로그인 성공 후 div -->\r\n");
      out.write("        \r\n");
      out.write("        <div id=\"userInfo\">\r\n");
      out.write("            <b>");
      out.print( loginUser.getUserName() );
      out.write("님 </b>의 방문을 환영합니다. <br><br>\r\n");
      out.write("\r\n");
      out.write("            <div align=\"center\">\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("/myPage.me\">마이페이지</a>\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("/logout.me\">로그아웃</a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <br clear=\"both\"> <br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"navWrap\" align=\"center\">\r\n");
      out.write("        <!-- (.menu>a)*4 -->\r\n");
      out.write("        <div class=\"menu\"><a href=\"");
      out.print(contextPath);
      out.write("\">HOME</a></div>\r\n");
      out.write("        <div class=\"menu\"><a href=\"");
      out.print(contextPath);
      out.write("/list.no\">공지사항</a></div>\r\n");
      out.write("        <div class=\"menu\"><a href=\"");
      out.print(contextPath);
      out.write("/list.bo?currentPage=1\">일반게시판</a></div>\r\n");
      out.write("        <div class=\"menu\"><a href=\"\">사진게시판</a></div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"outer\">\r\n");
      out.write("        <br>\r\n");
      out.write("        <h2 align=\"center\">공지사항 상세보기</h2>\r\n");
      out.write("        <br>\r\n");
      out.write("\r\n");
      out.write("        <table id=\"detailArea\" align=\"center\" border=\"1\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th width=\"70\">제목</th>\r\n");
      out.write("                <td colspan=\"3\">");
      out.print( n.getNoticeTitle() );
      out.write("</td>\r\n");
      out.write("                <!--  notice객체에 담겨있는 제목 뽑아오겠다 -->\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>작성자</th>\r\n");
      out.write("                <td>");
      out.print( n.getNoticeWriter() );
      out.write("</td>\r\n");
      out.write("                <th>작성일</th>\r\n");
      out.write("                <td>");
      out.print( n.getCreateDate() );
      out.write("</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>내용</th>\r\n");
      out.write("                <td colspan=\"3\">\r\n");
      out.write("                    <p>");
      out.print( n.getNoticeContent() );
      out.write("</p>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        <br><br>\r\n");
      out.write("\r\n");
      out.write("        <div align=\"center\">\r\n");
      out.write("        \t<!-- 모든 사용자 보여지기 가능 -->\r\n");
      out.write("            <a href=\"");
      out.print( contextPath );
      out.write("/list.no\" class=\"btn btn-secondary btn-sm\">목록가기</a>\r\n");
      out.write("\r\n");
      out.write("            <!-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우만 해당 -->\r\n");
      out.write("            ");
 if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())){ 
      out.write("\r\n");
      out.write("            <!-- loginUser null 이거나 userId=noticeWriter랑 같을 경우에만 수정,삭제 가능 -->\r\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("/updateForm.no?nno=");
      out.print( n.getNoticeNo() );
      out.write("\" class=\"btn btn-secondary btn-sm\">수정하기</a>\r\n");
      out.write("            <!-- updateForm으로 요청해서  url보면 nno라는 key값으로 공지사항글번호 잘 넘어오는지 확인-->\r\n");
      out.write("            <a href=\"");
      out.print(contextPath);
      out.write("/delete.no?nno=");
      out.print( n.getNoticeNo() );
      out.write("\" class=\"btn btn-secondary btn-sm\">삭제하기</a>\r\n");
      out.write("            ");
 } 
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
