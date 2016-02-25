package com.nhuszka.web.page;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.servlet.SearchServlet;

public class SearchPage implements Page {

	@Override
	public String getHTML() {
		StringBuilder startPageHtml = new StringBuilder();

		startPageHtml
		.append("<html>")
		.append("<body>")
		.append("<form action='").append("SearchServlet").append("' method='post' >")
		.append("Search word:")
		.append("<input type='text' name='").append(SearchServlet.PARAM_KEYWORD).append("' />")
		.append("<br/>")
		.append("<br/>")
		.append("Directory:")
		.append("<input type='text' name='").append(SearchServlet.PARAM_DIRECTORY).append("' />")
		.append("<br/>")
		.append("<br/>")
		.append("Extension:")
		.append("<input type='text' name='").append(SearchServlet.PARAM_EXTENSION).append("' />")
		.append("<br/>")
		.append("<br/>")
		.append("Algorithm: ");

		startPageHtml.append(getAlgorithmSelectorHTML());

		startPageHtml.append("<br/>")
		.append("<br/>")
		.append("<input type='submit' value='Search' />")
		.append("</form>")
		.append("</body>")
		.append("</html>");

		return startPageHtml.toString();
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
