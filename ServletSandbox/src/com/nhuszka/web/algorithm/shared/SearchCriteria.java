package com.nhuszka.web.algorithm.shared;

import com.nhuszka.web.algorithm.SearchAlgorithm;

public class SearchCriteria {

	private String root;
	private String keyword;
	private String extension;
	private SearchAlgorithm algorithm;

	public SearchCriteria(String root, String keyword, String extension, SearchAlgorithm algorithm) {
		this.root = root;
		this.keyword = keyword;
		this.extension = extension;
		this.algorithm = algorithm;
	}

	public String getRoot() {
		return root;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getExtension() {
		return extension;
	}
	
	public SearchAlgorithm getAlgorithm() {
		return algorithm;
	}
}
