package com.sample.filter;

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

/**
 * Servlet Filter implementation class Filtro
 */
//@WebFilter("/Filtro")
public class Filtro implements Filter {

	/**
	 * Default constructor.
	 */
	public Filtro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// place your code here

		// String r=request.get.getLocalAddr();

		String cp = (String) ((HttpServletRequest) request).getAttribute("user");// .getContextPath();
		if (cp!=null && cp.equals("admin")) {
			String halfPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(halfPath + "/paginas/info.xhtml");

		}else if(cp==null) {
			String halfPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(halfPath +"/login.xhtml");
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
