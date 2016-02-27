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
		
		body.append(getSearchHeader());
		body.append(getSearchResults());
		
		return body.toString();
	}
	
	private String getSearchHeader() {
		StringBuilder searchHeader = new StringBuilder();
		searchHeader.append("Keyword: ").append(searchCriteria.getKeyword()).append(BR);
		searchHeader.append("Directory/file: ").append(searchCriteria.getRoot()).append(BR);
		searchHeader.append("Extension: ").append(searchCriteria.getExtension()).append(BR);
		searchHeader.append("Algorithm: ").append(searchAlgorithm.getDescription()).append(BR);
		return searchHeader.toString();
	}

	private String getSearchResults() {
		StringBuilder searchResults = new StringBuilder();
		searchResults.append("Results:").append(BR);
		for (File file : searchAlgorithm.run(searchCriteria)) {
			searchResults.append(file.getAbsolutePath());
			searchResults.append(BR);
		}
		return searchResults.toString();
	}
}
