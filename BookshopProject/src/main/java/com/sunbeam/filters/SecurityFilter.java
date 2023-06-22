package com.sunbeam.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.Customer;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		// pre-processing = check if "cust" is added into session for protected resources
		if(!uri.endsWith("/index.html") && !uri.endsWith("/login")) {
			HttpSession session = req.getSession();
			
			// authentication
			Customer cust = (Customer) session.getAttribute("cust");
			if(cust == null) { // user is not authenticated
				resp.sendRedirect("index.html");
				return;
			}
			
			// authorization
			if(uri.endsWith("/books") || uri.endsWith("/editbook") || uri.endsWith("/delbook") || uri.endsWith("/addbook")) {
				if(!cust.getRole().equals("ROLE_ADMIN")) {
					resp.sendError(403);
					return;
				}
			}
		}
		chain.doFilter(request, response);
		// post-processing = do nothing
	}
}
