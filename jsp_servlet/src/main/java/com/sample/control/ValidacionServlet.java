package com.sample.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.model.LiquorSelect;
import com.sample.model.LiquorType;

@WebServlet(name = "validacionservlet", urlPatterns = "/Validation")
public class ValidacionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getContextPath();//req.getParameter("submit");//getRequestURL().toString();//.getRequestURI();//.getPathInfo();//.getQueryString();//.getLocalAddr();//.getRequestURI();//.getQueryString();//.getPathInfo();//.getContextPath();
		if (path.equals("/jsp_servlet")) {

			// resp.sendRedirect("result.jsp");
			// String liquorType = req.getParameter("Type");

			LiquorSelect liquorSelect = new LiquorSelect();
			LiquorType[] l = LiquorType.values();// .valueOf(liquorType);
			List ret = new ArrayList();
			for (int i = 0; i < l.length; i++) {

				List liquorBrands = liquorSelect.getAvailableBrands(l[i]);

				for (Object act : liquorBrands)
					ret.add(act);

			}
			req.setAttribute("brands", ret);

			RequestDispatcher view = req.getRequestDispatcher("result.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect("index.html");

		}
	}

}
