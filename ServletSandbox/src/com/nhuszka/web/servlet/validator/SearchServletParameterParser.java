package com.nhuszka.web.servlet.validator;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.exception.IllegalServletParameterException;
import com.nhuszka.web.servlet.SearchServlet;

public class SearchServletParameterParser {
	
	public static String getKeywordParameter(HttpServletRequest request) 
			throws IllegalServletParameterException {
		String keyword = request.getParameter(SearchServlet.PARAM_KEYWORD);
		checkEmpty(keyword, SearchServlet.PARAM_KEYWORD);
		return keyword;
	}

	public static String getRootParameter(HttpServletRequest request) 
			throws IllegalServletParameterException {
		String root = request.getParameter(SearchServlet.PARAM_ROOT);
		checkEmpty(root, SearchServlet.PARAM_ROOT);

		File file = new File(root);
		if (!file.exists()) {
			throw new IllegalServletParameterException("Error! Directory/file does not exist: " + root);
		}
		return root;
	}

	public static String getExtensionParameter(HttpServletRequest request) 
			throws IllegalServletParameterException {
		String extension = request.getParameter(SearchServlet.PARAM_EXTENSION);
		checkEmpty(extension, SearchServlet.PARAM_EXTENSION);
		return extension;
	}

	public static SearchAlgorithm getSearchAlgorithm(HttpServletRequest request)
			throws IllegalServletParameterException {
		String algorithm = request.getParameter(SearchServlet.PARAM_ALGORITHM);
		checkEmpty(algorithm, SearchServlet.PARAM_ALGORITHM);

		if (!SearchAlgorithm.existsAlgorithm(algorithm)) {
			throw new IllegalServletParameterException("Error! Algorithm does not exist: " + algorithm);
		}
		return SearchAlgorithm.valueOf(algorithm);
	}

	private static void checkEmpty(String value, String parameter) throws IllegalServletParameterException {
		if (value == null || value.isEmpty()) {
			throw new IllegalServletParameterException("Error: " + parameter + " should not be empty!");
		}
	}
}
