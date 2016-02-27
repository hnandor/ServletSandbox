package com.nhuszka.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.exception.IllegalServletParameterException;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String PARAM_KEYWORD = "keyword";
	public static final String PARAM_DIRECTORY = "directory";
	public static final String PARAM_ALGORITHM = "algorithm";
	public static final String PARAM_EXTENSION = "extension";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String keyword = request.getParameter(PARAM_KEYWORD);
		final String directory = request.getParameter(PARAM_DIRECTORY);
		final String algorithm = request.getParameter(PARAM_ALGORITHM);
		final String extension = request.getParameter(PARAM_EXTENSION);

		final SearchAlgorithm searchAlgorithm = parseAlgorithm(algorithm);
		SearchCriteria searchCriteria = getSearchCriteria(directory, keyword, extension);

		PrintWriter writer = response.getWriter();
		writer.append(computeSearchHeader(searchAlgorithm, searchCriteria));
		writer.append(computeSearchResults(searchAlgorithm, searchCriteria));
	}

	private SearchAlgorithm parseAlgorithm(String algorithm) throws ServletException {
		try {
			SearchAlgorithm.checkExistingAlgorithm(algorithm);
			return SearchAlgorithm.valueOf(algorithm);
		} catch (IllegalServletParameterException ispe) {
			throw new ServletException(ispe.getMessage());
		}
	}

	private SearchCriteria getSearchCriteria(String directory, String keyword, String extension) {
		return new SearchCriteria(directory, keyword, extension);
	}
	
	private String computeSearchHeader(SearchAlgorithm searchAlgorithm, SearchCriteria searchCriteria) {
		StringBuilder searchHeader = new StringBuilder();
		searchHeader.append("Keyword: ").append(searchCriteria.getKeyword()).append("<br/>");
		searchHeader.append("Directory: ").append(searchCriteria.getDirectory()).append("<br/>");
		searchHeader.append("Extension: ").append(searchCriteria.getExtension()).append("<br/>");
		searchHeader.append("Algorithm: ").append(searchAlgorithm.getDescription()).append("<br/>");
		return searchHeader.toString();
	}
	
	private String computeSearchResults(SearchAlgorithm searchAlgorithm, SearchCriteria searchCriteria) {
		StringBuilder searchResults = new StringBuilder();
		searchResults.append("Results:").append("<br/>");
		for (File file : searchAlgorithm.run(searchCriteria)) {
			searchResults.append(file.getAbsolutePath());
			searchResults.append("<br/>");
		}
		return searchResults.toString();
	}
}
