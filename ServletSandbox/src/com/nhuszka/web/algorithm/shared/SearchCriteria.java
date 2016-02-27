package com.nhuszka.web.algorithm.shared;

public class SearchCriteria {

	private String directory;
	private String keyword;
	private String extension;

	public SearchCriteria(String directory, String keyword, String extension) {
		this.directory = directory;
		this.keyword = keyword;
		this.extension = extension;
	}

	public String getDirectory() {
		return directory;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getExtension() {
		return extension;
	}
}
