/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.64
 * Generated at: 2021-04-12 06:00:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;
import com.kh.member.model.vo.Member;

public final class boardListView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/board/../common/menubar.jsp", Long.valueOf(1618206858355L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.kh.common.model.vo.PageInfo");
    _jspx_imports_classes.add("com.kh.board.model.vo.Board");
    _jspx_imports_classes.add("java.util.ArrayList");
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
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP?????? ?????? GET, POST ?????? HEAD ??????????????? ???????????????. Jasper??? OPTIONS ????????? ?????? ???????????????.");
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

      out.write("\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    \r\n");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();

      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("    .outer{\r\n");
      out.write("        width:1000px;\r\n");
      out.write("        height:550px;\r\n");
      out.write("        background: black;\r\n");
      out.write("        color:white;\r\n");
      out.write("        margin:auto;\r\n");
      out.write("        margin-top: 50px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .listArea{\r\n");
      out.write("        border:1px solid white;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .listArea>tbody>tr:hover{\r\n");
      out.write("        background: gray;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");

	// Member ?????? ????????? session??? loginUser ??????
	Member loginUser = (Member)session.getAttribute("loginUser");
	// > ????????? ??? menubar.jsp ????????? : loginUser => null
	// > ????????? ?????? ??? menubar.jsp ????????? : loginUser => ???????????? ????????? ???????????? ???????????? ??????
	// > ??? ???????????? include?????? ?????? ??? loginUser?????? ??????????????? ????????? ??? ?????????!
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
      out.write("\"; // ??????????????? ????????? ?????????\r\n");
      out.write("\t\t// var msg = \"?????????\"   / \"null\";\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(msg != \"null\"){\r\n");
      out.write("\t\t\talert(msg);\r\n");
      out.write("\t\t\t// session??? ??? ??? ????????? ??? ?????? ????????? ??? ?????? ?????? ???????????? (????????? ????????? ??? ???????????? ??????????????? alert ?????? ??? ???)\r\n");
      out.write("\t\t\t// ????????? ????????? ?????? session??? ?????? ????????? ????????????!!\r\n");
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
      out.write("\t\t<!-- loginUser ?????? null ????????? ?????? ????????????, ????????? ??? ?????? ????????? -->\r\n");
      out.write("\t\t");
 if(loginUser == null){ 
      out.write("\r\n");
      out.write("        <!-- 1. ????????? ?????? ???????????? ????????? form -->\r\n");
      out.write("        <!-- ??????????????? \"/jsp\" ?????? request.getContextPath() ???  ??????-->\r\n");
      out.write("        <!-- method=\"post\" : ????????? ??? url??? ??????x -->        \r\n");
      out.write("        <form action=\"");
      out.print( contextPath );
      out.write("/login.me\" id=\"loginForm\" method=\"post\">\r\n");
      out.write("            <table>\r\n");
      out.write("\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th><label for=\"userId\">????????? : </label></th>\r\n");
      out.write("                    <td><input type=\"text\" name=\"userId\" id=\"userId\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th><label for=\"userPwd\">???????????? : </label></th>\r\n");
      out.write("                    <td><input type=\"password\" name=\"userPwd\" id=\"userPwd\" required></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th colspan=\"2\">\r\n");
      out.write("                        <button type=\"submit\">?????????</button>\r\n");
      out.write("                        <button type=\"button\" onclick=\"enrollPage();\">????????????</button>\r\n");
      out.write("                    </th>\r\n");
      out.write("                    <script>\r\n");
      out.write("                    \tfunction enrollPage(){\r\n");
      out.write("                    \t\t//loacation.href=\"/jsp/views/member/memberEnrollForm.jsp\";\r\n");
      out.write("                    \t\t// ??? ????????????????????? ???????????? ????????? url??? ???????????? ????????? ??????\r\n");
      out.write("                    \t\t\r\n");
      out.write("                    \t\t// ????????? ????????? ????????? ??????????????? ?????? ????????? servlet????????? forwarding ???????????? ??????\r\n");
      out.write("                    \t\t// => url??? servlet???????????? ????????????!\r\n");
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
      out.write("        <!-- 2. ????????? ?????? ??? div -->\r\n");
      out.write("        \r\n");
      out.write("        <div id=\"userInfo\">\r\n");
      out.write("            <b>");
      out.print( loginUser.getUserName() );
      out.write("??? </b>??? ????????? ???????????????. <br><br>\r\n");
      out.write("\r\n");
      out.write("            <div align=\"center\">\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("/myPage.me\">???????????????</a>\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("/logout.me\">????????????</a>\r\n");
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
      out.write("/list.no\">????????????</a></div>\r\n");
      out.write("        <div class=\"menu\"><a href=\"");
      out.print(contextPath);
      out.write("/list.bo?currentPage=1\">???????????????</a></div>\r\n");
      out.write("        <div class=\"menu\"><a href=\"");
      out.print(contextPath);
      out.write("/list.th\">???????????????</a></div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"outer\">\r\n");
      out.write("        <br>\r\n");
      out.write("        <h2 align=\"center\">???????????????</h2>\r\n");
      out.write("        <br>\r\n");
      out.write("\r\n");
      out.write("        <!-- ????????????????????? ???????????? div -->\r\n");
      out.write("        ");
 if(loginUser != null) {
      out.write("\r\n");
      out.write("        <div align=\"right\" style=\"width:850px\">\r\n");
      out.write("           <a href=\"");
      out.print(contextPath);
      out.write("/enrollForm.bo\" class=\"btn btn-secondary btn-sm\">?????????</a> \r\n");
      out.write("           <br><br>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("        <table align=\"center\" class=\"listArea\">\r\n");
      out.write("            <thead>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th width=\"70\">?????????</th>\r\n");
      out.write("                    <th width=\"70\">????????????</th>\r\n");
      out.write("                    <th width=\"300\">??????</th>\r\n");
      out.write("                    <th width=\"100\">?????????</th>\r\n");
      out.write("                    <th width=\"60\">?????????</th>\r\n");
      out.write("                    <th width=\"100\">?????????</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </thead>\r\n");
      out.write("            <tbody>\r\n");
      out.write("                <!-- ????????? ????????? ?????? ?????? -->\r\n");
      out.write("                ");
 if(list.isEmpty()) { 
      out.write("\r\n");
      out.write("                    <tr>\r\n");
      out.write("                    <td colspan=\"6\">????????? ???????????? ????????????.</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                ");
 } else { 
      out.write("\r\n");
      out.write("                <!-- ????????? ????????? ?????? ?????? -->\r\n");
      out.write("\t            \t");
 for(Board b : list){ 
      out.write("    \r\n");
      out.write("\t\t                <tr>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getBoardNo() );
      out.write("</td>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getCategoryNo() );
      out.write("</td>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getBoardTitle() );
      out.write("</td>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getBoardWriter());
      out.write("</td>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getCount() );
      out.write("</td>\r\n");
      out.write("\t\t                    <td>");
      out.print( b.getCreateDate() );
      out.write("</td>\r\n");
      out.write("\t\t                </tr>\r\n");
      out.write("\t                ");
 } 
      out.write("\r\n");
      out.write("\t         \t");
 } 
      out.write("\r\n");
      out.write("             </tbody>\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\t$(function(){\r\n");
      out.write("\t\t\t\t$(\".listArea>tbody>tr\").click(function(){\r\n");
      out.write("\t\t\t\t\t// /jsp/detail.bo?bno=?????????\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tlocation.href = '");
      out.print(contextPath);
      out.write("/detail.bo?bno=' +$(this).children().eq(0).text() ; \r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t})\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t\r\n");
      out.write("        <br><br>\r\n");
      out.write("\r\n");
      out.write("        <div align=\"center\" class=\"pagingArea\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");
 if(currentPage != 1) { 
      out.write("        \r\n");
      out.write("        \t<button onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(currentPage-1);
      out.write("';\">??????</button>\r\n");
      out.write("        \t");
 } 
      out.write("\r\n");
      out.write("        \t\r\n");
      out.write("            ");
 for(int p=startPage; p<=endPage; p++){ 
      out.write("\r\n");
      out.write("            \r\n");
      out.write("\t            ");
 if(currentPage == p){ 
      out.write("\r\n");
      out.write("\t            \t<button disabled>");
      out.print( p );
      out.write("</button>\r\n");
      out.write("\t            ");
 } else { 
      out.write("\r\n");
      out.write("\t            \t<button onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(p);
      out.write("';\">");
      out.print(p);
      out.write("</button>\r\n");
      out.write("\t            ");
 } 
      out.write("\r\n");
      out.write("\t        ");
 } 
      out.write("\r\n");
      out.write("\t\t\t");
 if(currentPage != maxPage){ 
      out.write("\t        \r\n");
      out.write("\t        <button onclick=\"location.href='");
      out.print(contextPath);
      out.write("/list.bo?currentPage=");
      out.print(currentPage+1);
      out.write("';\">??????</button>\r\n");
      out.write("\t        ");
 } 
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
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
