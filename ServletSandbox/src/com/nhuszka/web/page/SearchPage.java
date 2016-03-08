package com.nhuszka.web.page;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.servlet.SearchServlet;

public class SearchPage extends Page {
	
	private Object errorMessage;
	
	public SearchPage() {
	}
	
	public SearchPage(Object errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String getHTMLBody() {
		StringBuilder body = new StringBuilder();
		body.append(DIV_FLUID);
		body.append(String.format(TITLE, "Search page"));
		body.append(BR);
		
		if (errorMessage != null) {
			body.append(errorMessage);
		}

		body.append("<form action='").append("SearchServlet").append("' method='post' >")
				.append(TABLE_STRIPED);
		
		body.append(TR).append(TD).append("Keyword:")
				.append(TD_END)
				.append(TD)
				.append("<input type='text' name='").append(SearchServlet.PARAM_KEYWORD).append("' />")
				.append(TD_END)
				.append(TR_END);
		
		body.append(TR).append(TD).append("Directory/file:")
				.append(TD_END)
				.append(TD)
				.append("<input type='text' name='").append(SearchServlet.PARAM_ROOT).append("' />")
				.append(TD_END);
		
		body.append(TR).append(TD).append("Extension:")
				.append(TD_END)
				.append(TD)
				.append("<input type='text' name='").append(SearchServlet.PARAM_EXTENSION).append("' />")
				.append(TD_END)
				.append(TR_END);
		
		body.append(TR)
				.append(TD)
				.append("Algorithm: ")
				.append(TD_END)
				.append(TD)
				.append(getAlgorithmSelectorHTML())
				.append(TD_END)
				.append(TR_END);
				
		body.append(TR)
				.append(TD)
				.append(TD_END)
				.append(TD)
				.append("<button type='submit' class='btn btn-default'>Search</button>")
				.append(TD_END)
				.append(TR_END)
				.append(TABLE_END)
				.append("</form>");
		
		body.append(DIV_END);
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
