package com.sunbeam.bookshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.BookDao;
@WebServlet("/delbook")
public class DeleteBook  extends HttpServlet{

 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String bookid = req.getParameter("id");
	
	if(bookid != null) {
		int id = Integer.parseInt(bookid);
	try(BookDao dao = new BookDao()){
		
		dao.deleteById(id);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	resp.sendRedirect("books");
	 
	 
}
 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	
	
	
}
