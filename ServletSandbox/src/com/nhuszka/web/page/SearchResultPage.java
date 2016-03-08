package com.nhuszka.web.page;

import java.io.File;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class SearchResultPage extends Page {

	private SearchAlgorithm searchAlgorithm;
	private SearchCriteria searchCriteria;
	
	public SearchResultPage(SearchAlgorithm searchAlgorithm, SearchCriteria searchCriteria) {
		this.searchAlgorithm = searchAlgorithm;
		this.searchCriteria = searchCriteria;
	}
	
	@Override
	public String getHTMLBody() {
		StringBuilder body = new StringBuilder();
		body.append(DIV_FLUID);
		
		body.append(String.format(TITLE, "Search result page"));
		
		body.append(TABLE_STRIPED);
		body.append(getSearchHeader());
		body.append(TABLE_END);
		body.append(getSearchResults());
		
		body.append(DIV_END);
		return body.toString();
	}
	
	private String getSearchHeader() {
		StringBuilder searchHeader = new StringBuilder();
		searchHeader.append(TR)
			.append(TD).append("Keyword: ").append(TD_END)
			.append(TD).append(searchCriteria.getKeyword()).append(TD_END);
		searchHeader.append(TR_END);
		
		searchHeader.append(TR)
			.append(TD).append("Directory/file: ").append(TD_END)
			.append(TD).append(searchCriteria.getRoot()).append(TD_END);
		searchHeader.append(TR_END);
		
		searchHeader.append(TR)
			.append(TD).append("Extension: ").append(TD_END)
			.append(TD).append(searchCriteria.getExtension()).append(TD_END);
		searchHeader.append(TR_END);
		
		searchHeader.append(TR)
			.append(TD).append("Algorithm: ").append(TD_END)
			.append(TD).append(searchAlgorithm.getDescription()).append(TD_END);
		searchHeader.append(TR_END);
		
		searchHeader.append(BR);
		return searchHeader.toString();
	}

	private String getSearchResults() {
		StringBuilder searchResults = new StringBuilder();
		searchResults.append(BR).append("Results:").append(BR).append(BR);
		for (File file : searchAlgorithm.run(searchCriteria)) {
			searchResults.append(file.getAbsolutePath());
			searchResults.append(BR);
		}
		return searchResults.toString();
	}
}
