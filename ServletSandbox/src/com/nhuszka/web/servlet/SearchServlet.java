package com.nhuszka.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String searchWord = request.getParameter("searchWord");
		String directory = request.getParameter("directory");
		String algorithm = request.getParameter("algorithm");

		response.getWriter()
			.append("Search word: ")
			.append(searchWord)
			.append("<br/>")
			.append("Directory: ")
			.append(directory)
			.append("<br/>")
			.append("Algorithm: ")
			.append(algorithm)
			.append("<br/>");
	}
}
