package com.nhuszka.web.page;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.servlet.SearchServlet;

public class SearchPage extends Page {

	@Override
	public String getHTMLBody() {
		StringBuilder body = new StringBuilder();

		body.append("<form action='").append("SearchServlet").append("' method='post' >")
				.append("Keyword:")
				.append("<input type='text' name='").append(SearchServlet.PARAM_KEYWORD).append("' />")
				.append(BR).append(BR)
				.append("Directory/file:")
				.append("<input type='text' name='").append(SearchServlet.PARAM_ROOT).append("' />")
				.append(BR).append(BR)
				.append("Extension:")
				.append("<input type='text' name='").append(SearchServlet.PARAM_EXTENSION).append("' />")
				.append(BR).append(BR)
				.append("Algorithm: ");

		body.append(getAlgorithmSelectorHTML());

		body.append(BR)
				.append(BR)
				.append("<input type='submit' value='Search' />")
				.append("</form>");

		return body.toString();
	}

	private String getAlgorithmSelectorHTML() {
		StringBuilder selectorHTML = new StringBuilder();

		selectorHTML.append("<select name='").append(SearchServlet.PARAM_ALGORITHM).append("'>");
		for (SearchAlgorithm algorithm : SearchAlgorithm.values()) {
			selectorHTML.append("<option value='").append(algorithm.name()).append("'>");
			selectorHTML.append(algorithm.getDescription());
			selectorHTML.append("</option>");
		}
		selectorHTML.append("</select>");

		return selectorHTML.toString();
	}
}
