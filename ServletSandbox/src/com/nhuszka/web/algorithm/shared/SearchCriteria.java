package com.nhuszka.web.algorithm.shared;

public class SearchCriteria {

	private String root;
	private String keyword;
	private String extension;

	public SearchCriteria(String root, String keyword, String extension) {
		this.root = root;
		this.keyword = keyword;
		this.extension = extension;
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
}
