package com.sunbeam.bookshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.Book;
import com.sunbeam.daos.BookDao;
@WebServlet("/addbook")
public class AddBookServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String subject = req.getParameter("subject");
		String pr = req.getParameter("price");
		double price = Double.parseDouble(pr);
		
		try(BookDao dao = new BookDao()){
			
			Book b = new Book(0,name,author,subject,price);
			dao.save(b);
			resp.sendRedirect("books");
			
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
