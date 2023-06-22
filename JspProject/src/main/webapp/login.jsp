<%@page import="com.sunbeam.daos.CustomerDao"%>
<%@page import="com.sunbeam.daos.Customer"%>
<%
String email = request.getParameter("email");
String password = request.getParameter("passwd");
try(CustomerDao dao = new CustomerDao()) {
Customer cust = dao.findByEmail(email);
if(cust!=null && cust.getPassword().equals(password)) {
	if(cust.getRole().equals("ROLE_CUSTOMER")){
		response.sendRedirect("books.jsp");
	}
	else if(cust.getRole().equals("ROLE_ADMIN")){
		response.sendRedirect("admin.jsp");
	}
%>
 
<%
} else { %>
Login Failed.
<%
}
}
catch(Exception ex) {
// ...
}
%>