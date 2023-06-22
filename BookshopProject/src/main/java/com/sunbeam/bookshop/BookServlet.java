package com.sunbeam.bookshop;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.Book;
import com.sunbeam.daos.BookDao;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
		
		
		try(BookDao dao = new BookDao()){
			List<Book> book = new ArrayList<Book>();
			
			book = dao.findAll();
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Subject Books</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form method='post' action='add'>");
			out.println("<table border = '1' >");
			out.println("<thead>");
			out.println("<th>id</th>");
			out.println("<th>Name</th>");
			out.println("<th>Author</th>");
			out.println("<th>Subject</th>");
			out.println("<th>Price</th>");
			out.println("</thead>");
			
			
			out.print("<tbody>");
			String uname = "unknown";
			Cookie[] arr = req.getCookies();
			if (arr != null) {

				for (Cookie c : arr) {
					if (c.getName().equals("uname")) {
						uname = c.getValue();
						break;
					}
				}
			}
			out.printf("Hello,%s <hr/>\r\n", uname);
			for (Book b : book) {
				out.println("<tr>");
				out.printf("<td>%d</td>", b.getId());
				out.printf("<td>%s</td>", b.getName());
				out.printf("<td>%s</td>", b.getAuthor());
				out.printf("<td>%s</td>", b.getSubject());
				out.printf("<td>%.2f</td>", b.getPrice());
				out.println("<td>");
				out.printf("<a href='editbook?id=%d'>EDIT</a>\r\n", b.getId());
				out.printf("<a href='delbook?id=%d'>DELETE</a>\r\n", b.getId());
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href='book.html'>Add Book</a>");
		
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");	
			
			
		 }catch(Exception e) {
			
			
		}
		
		
	}
	
	
}
