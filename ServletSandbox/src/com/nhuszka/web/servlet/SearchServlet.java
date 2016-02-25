package com.nhuszka.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.exception.IllegalServletParameterException;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String PARAM_KEYWORD = "keyword";
	public static final String PARAM_DIRECTORY = "directory";
	public static final String PARAM_ALGORITHM = "algorithm";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String searchWord = request.getParameter(PARAM_KEYWORD);
		final String directory = request.getParameter(PARAM_DIRECTORY);
		final String algorithm = request.getParameter(PARAM_ALGORITHM);

		final SearchAlgorithm searchAlgorithm = parseAlgorithm(algorithm);

		response.getWriter().append("Keyword: ").append(searchWord).append("<br/>").append("Directory: ")
				.append(directory).append("<br/>").append("Algorithm: ").append(searchAlgorithm.getDescription())
				.append("<br/>");

		callAlgorithm(searchAlgorithm, searchWord, directory);

	}

	private SearchAlgorithm parseAlgorithm(String algorithm) throws ServletException {
		try {
			SearchAlgorithm.checkExistingAlgorithm(algorithm);
			return SearchAlgorithm.valueOf(algorithm);
		} catch (IllegalServletParameterException ispe) {
			throw new ServletException(ispe.getMessage());
		}
	}

	private void callAlgorithm(SearchAlgorithm searchAlgorithm, String searchWord, String directory) {
		// TODO move the logic into Enum
		switch (searchAlgorithm) {
		case SINGLE_THREAD:
			break;
		case PARALLEL:
			break;
		case FORK_JOIN:
			break;
		}
	}
}
