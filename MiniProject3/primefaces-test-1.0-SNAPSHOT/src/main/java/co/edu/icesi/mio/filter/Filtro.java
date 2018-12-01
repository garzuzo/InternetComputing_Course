package co.edu.icesi.mio.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.edu.icesi.mio.bean.Login;

@WebFilter("/Filtro")
public class Filtro implements Filter {

	private ServletContext sc;

	public Filtro() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		sc = filterConfig.getServletContext();

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

	//	String val=(String) sc.getAttribute("username");
		HttpSession session = req.getSession(false);

		if (session == null) {

			String halfPath = req.getContextPath();

			((HttpServletResponse) response).sendRedirect(halfPath + "/Login.xhtml");
			sc.log("Intento de acceso no autorizado");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
