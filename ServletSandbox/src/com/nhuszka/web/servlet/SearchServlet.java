package com.nhuszka.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.exception.IllegalServletParameterException;
import com.nhuszka.web.page.ErrorPage;
import com.nhuszka.web.page.Page;
import com.nhuszka.web.page.SearchResultPage;
import com.nhuszka.web.servlet.validator.SearchServletParameterParser;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String PARAM_KEYWORD = "Keyword";
	public static final String PARAM_ROOT = "Directory/file";
	public static final String PARAM_ALGORITHM = "Algorithm";
	public static final String PARAM_EXTENSION = "Extension";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			String keyword = SearchServletParameterParser.getKeywordParameter(request);
			String root = SearchServletParameterParser.getRootParameter(request);
			String extension = SearchServletParameterParser.getExtensionParameter(request);
			SearchCriteria searchCriteria = new SearchCriteria(root, keyword, extension);

			SearchAlgorithm searchAlgorithm = SearchServletParameterParser.getSearchAlgorithm(request);			
			
			Page searchResultPage = new SearchResultPage(searchAlgorithm, searchCriteria);
			writer.append(searchResultPage.generateHTML());
		} catch (IllegalServletParameterException ispe) {
			Page errorPage = new ErrorPage(ispe.getMessage());
			writer.append(errorPage.generateHTML());
		}
	}
}
