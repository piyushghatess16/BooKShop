package com.sunbeam.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.BookDao;
import com.sunbeam.daos.Customer;

@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Set<String> subjects = new LinkedHashSet<>();

		try (BookDao dao = new BookDao()) {
			subjects = dao.findAllSubjects();

		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subjects</title>");
		out.println("</head>");
		out.println("<body>");
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
		out.printf("Hello,%s <hr/>/r/n", uname);
		out.println("<form method='post' action='subbooks'>");
		for (String subject : subjects)
			out.printf("<input type='radio' name='subject' value='%s'/> %s <br/>\r\n", subject, subject);
		out.println("<input type='submit' value='Show Books'/>");
		out.println("</form>");
		out.println("<a href='showcart'>Show Cart</a>");
		// get new books count added into cart from addcart servlet
		Integer newBookCount = (Integer) req.getAttribute("newbookcount");
		if (newBookCount != null)
			out.printf("<br/> %d more books added into cart.", newBookCount);
		out.println("</body>");

		// get cust info from session and display it
		HttpSession session = req.getSession();
		Customer cust = (Customer) session.getAttribute("cust");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		out.printf("Email: %s, Mobile: %s, Address: %s, Birth: %s", cust.getEmail(), cust.getMobile(),
				cust.getAddress(), sdf.format(cust.getBirth()));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
