package com.sunbeam.bookshop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.Customer;
import com.sunbeam.daos.CustomerDao;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String birthdate = req.getParameter("birth");
//		name,email,address,
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date dt =null;
		try {
			dt = sdf.parse(birthdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String id1 = req.getParameter("id");
//		int id = Integer.parseInt(id1);
		String name = req.getParameter("name");
		String pass = req.getParameter("passwd");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
//		String enabled = req.getParameter("enabled");
//		int enb = Integer.parseInt("enabled");
		
		String role = req.getParameter("role");
		Customer c = new Customer(0,name,pass,mobile,address,email,dt,1,role);
		
		try(CustomerDao dao = new CustomerDao()){
			dao.save(c);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		//int id, String name, String password, String mobile, String address, String email, Date birth,
	//	int enabled, String role
		
	
		
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
