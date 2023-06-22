package com.sunbeam.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.Book;
import com.sunbeam.daos.BookDao;
import com.sunbeam.daos.Customer;
@WebServlet("/subbooks")
public class SubjectBooksServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
		
		String subject = req.getParameter("subject");
		List<Book> books = new ArrayList<>();
		try(BookDao dao = new BookDao()) {
			books = dao.findBySubject(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subject Books</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='addcart'>");
		for(Book b: books)
			out.printf("<input type='checkbox' name='book' value='%d'/> %s (%s)<br/>\r\n", 
												b.getId(), b.getName(), b.getAuthor());
		out.println("<input type='submit' value='Add Cart'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");	
		// get cookie from client and get value of uname cookie and use to greet user
			String uname = "Unknown";
			Cookie[] arr = req.getCookies();
			if(arr != null) {
				for(Cookie c:arr) {
					if(c.getName().equals("uname")) {
						uname = c.getValue();
						break;
					}
				}
			}
			out.printf("Hello, %s <hr/>\r\n", uname);
			// get cust info from session and display it
				HttpSession session = req.getSession();
				Customer cust = (Customer) session.getAttribute("cust");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		        out.printf("Email: %s, Mobile: %s, Address: %s, Birth: %s<hr/>\r\n",
				cust.getEmail(), cust.getMobile(), cust.getAddress(), sdf.format(cust.getBirth()));
		        
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
		
		
		
		
		
	}
	
	


