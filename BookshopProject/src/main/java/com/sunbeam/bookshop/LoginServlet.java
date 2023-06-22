package com.sunbeam.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.Customer;
import com.sunbeam.daos.CustomerDao;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
		
		String email = req.getParameter("email");
		
		String password = req.getParameter("passwd");
		
		System.out.println("password="+password);
		System.out.println("email="+email);
	
		try(CustomerDao dao = new CustomerDao()){
			Customer dbcust = dao.findByEmail(email);
			
			if(dbcust != null && dbcust.getPassword().equals(password)) {
				//store username in a cookie and send it to client 
				Cookie c  = new Cookie("uname",dbcust.getName());
				      c.setMaxAge(3600);
				      resp.addCookie(c);
				      
				    
				   // store customer into session
				HttpSession session = req.getSession();
				session.setAttribute("cust", dbcust);
				
				List<Integer> cart = new ArrayList();
			     session.setAttribute("cart",cart);//	store an empty cart into the session
				

				
				
				if(dbcust.getRole().equals("ROLE_CUSTOMER")) {
					resp.sendRedirect("subject");
				}
				else if(dbcust.getRole().equals("ROLE_ADMIN")) {
				resp.sendRedirect("books");
				}
				else
					resp.sendRedirect("index.html");
				
			}
			else {
				resp.setContentType("text/html");
			PrintWriter out =resp.getWriter();	
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Login failed </title>");
			out.println("</head>");
			out.println("<body>");
//			out.println("Sorry,%s<br/>\r\n",email);
			out.printf("Sorry, %s<br/>\r\n", email);
			out.println("Inavalid email or password.<br/><br/>");
			out.println("</body>");
			out.println("</html>");
			
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
