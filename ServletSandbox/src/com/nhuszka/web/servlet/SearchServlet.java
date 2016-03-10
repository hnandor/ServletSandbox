package com.nhuszka.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.exception.IllegalServletParameterException;
import com.nhuszka.web.page.PagePath;
import com.nhuszka.web.servlet.validator.SearchServletParameterParser;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String PARAM_KEYWORD = "keyword";
	public static final String PARAM_ROOT = "directoryFile";
	public static final String PARAM_ALGORITHM = "algorithm";
	public static final String PARAM_EXTENSION = "extension";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String keyword = SearchServletParameterParser.getKeywordParameter(request);
			String root = SearchServletParameterParser.getRootParameter(request);
			String extension = SearchServletParameterParser.getExtensionParameter(request);
			SearchCriteria searchCriteria = new SearchCriteria(root, keyword, extension);

			SearchAlgorithm searchAlgorithm = SearchServletParameterParser.getSearchAlgorithm(request);			
			
			request.setAttribute("criteria", searchCriteria);
			request.setAttribute("algorithm", searchAlgorithm.getDescription());
			request.setAttribute("filePaths", searchAlgorithm.run(searchCriteria));
			request.getRequestDispatcher(PagePath.SEARCH_RESULT_PAGE).include(request, response);
		} catch (IllegalServletParameterException ispe) {
			request.setAttribute(StartServlet.ERROR_MSG, ispe.getMessage());
			request.getRequestDispatcher("StartServlet").include(request, response);
		}
	}
}
