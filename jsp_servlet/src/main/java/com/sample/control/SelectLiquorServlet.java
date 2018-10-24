package com.sample.control;

import com.sample.model.LiquorSelect;
import com.sample.model.LiquorType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "selectliquorservlet", urlPatterns = "/SelectLiquor")
public class SelectLiquorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getQueryString() == null) {

			resp.sendRedirect("index.html");
			// RequestDispatcher view = req.getRequestDispatcher("index.html");
			// view.forward(req, resp);
		} else {
			doPost(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String liquorType = req.getParameter("Type");

		LiquorSelect liquorSelect = new LiquorSelect();
		LiquorType l = LiquorType.valueOf(liquorType);

		List liquorBrands = liquorSelect.getAvailableBrands(l);

		req.setAttribute("brands", liquorBrands);
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		view.forward(req, resp);

	}
}