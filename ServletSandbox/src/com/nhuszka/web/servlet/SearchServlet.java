package com.nhuszka.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.StartForkJoinMultiThreadFileSearcher;
import com.nhuszka.web.algorithm.StartParallelFileSearcher;
import com.nhuszka.web.algorithm.StartRecursiveSingleThreadFileSearcher;
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
		final String searchWord = request.getParameter(PARAM_KEYWORD);
		final String directory = request.getParameter(PARAM_DIRECTORY);
		final String algorithm = request.getParameter(PARAM_ALGORITHM);
		final String extension = request.getParameter(PARAM_EXTENSION);

		final SearchAlgorithm searchAlgorithm = parseAlgorithm(algorithm);

		PrintWriter writer = response.getWriter();
		writer.append("Keyword: ").append(searchWord).append("<br/>").append("Directory: ")
		.append(directory).append("<br/>").append("Extension: ")
				.append(extension).append("<br/>").append("Algorithm: ").append(searchAlgorithm.getDescription())
		.append("<br/>");
		writer.append("Results:").append("<br/>");

		Collection<File> files = callAlgorithm(searchAlgorithm, searchWord, directory, extension);
		for (File file : files) {
			writer.append(file.getAbsolutePath()).append("<br/>");
		}
	}

	private SearchAlgorithm parseAlgorithm(String algorithm) throws ServletException {
		try {
			SearchAlgorithm.checkExistingAlgorithm(algorithm);
			return SearchAlgorithm.valueOf(algorithm);
		} catch (IllegalServletParameterException ispe) {
			throw new ServletException(ispe.getMessage());
		}
	}

	private Collection<File> callAlgorithm(SearchAlgorithm searchAlgorithm, String searchWord, String directory,
			String extension) {
		// TODO move the logic into Enum
		Collection<File> files = new ArrayList<>();
		switch (searchAlgorithm) {
			case SINGLE_THREAD:
				files = StartRecursiveSingleThreadFileSearcher.run(directory, searchWord, extension);
				break;
			case PARALLEL:
				files = StartParallelFileSearcher.run(directory, searchWord, extension);
				break;
			case FORK_JOIN:
				files = StartForkJoinMultiThreadFileSearcher.run(directory, searchWord, extension);
				break;
		}
		return files;
	}
}
