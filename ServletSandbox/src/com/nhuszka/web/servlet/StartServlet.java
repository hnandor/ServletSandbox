package com.nhuszka.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.page.PagePath;
import com.nhuszka.web.algorithm.SearchAlgorithm;

public class StartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String ERROR_MSG = "errorMsg";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		forwardToSearchPage(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", parseErrorAttribute(request));
		forwardToSearchPage(request, response);
	}

	private void forwardToSearchPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("algorithms", SearchAlgorithm.values());
		request.getRequestDispatcher(PagePath.SEARCH_PAGE).forward(request, response);
	}

	// TODO: validate to not contain script, etc.
	private String parseErrorAttribute(HttpServletRequest request) {
		Object errorAttribute = request.getAttribute(ERROR_MSG);
		if (errorAttribute instanceof String) {
			return (String) errorAttribute;
		}
		return null;
	}
}
