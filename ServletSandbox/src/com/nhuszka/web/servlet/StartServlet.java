package com.nhuszka.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.page.Page;
import com.nhuszka.web.page.SearchPage;

public class StartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_MSG = "errorMsg";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page searchPage = new SearchPage();
		response.getWriter().append(searchPage.generateHTML());	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page searchPage = new SearchPage(parseErrorAttribute(request));
		response.getWriter().append(searchPage.generateHTML());
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
