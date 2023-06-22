package com.sunbeam.bookshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout")
public class LogoutServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	 delete user cart and other details 
//	destroy uname cookie from client 

		HttpSession session = req.getSession();
		session.invalidate();
		
		PrintWriter out = resp.getWriter();
		out.print("<head>");
		Cookie c = new Cookie("uname","");
		c.setMaxAge(-1);
		resp.addCookie(c);
		
		
		ServletContext ctx = req.getServletContext();
		String title = ctx.getInitParameter("app.title");
		out.printf("<h3>%s</h3>\r\n", title);

		out.println("Thank you for using our online bookshop.<br/><br/>");
		out.println("<a href='index.html'>Login Again</a>");
		out.println("</body>");
		out.println("</html>");		
		
		
		
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doGet(req, resp);
	}
	

}
