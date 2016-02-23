package com.nhuszka.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String PARAM_KEYWORD = "keyword";
	public static final String PARAM_DIRECTORY = "directory";
	public static final String PARAM_ALGORITHM = "algorithm";


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String searchWord = request.getParameter(PARAM_KEYWORD);
		String directory = request.getParameter(PARAM_DIRECTORY);
		String algorithm = request.getParameter(PARAM_ALGORITHM);

		response.getWriter()
			.append("Keyword: ")
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
