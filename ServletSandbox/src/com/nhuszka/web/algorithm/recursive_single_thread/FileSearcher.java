package com.nhuszka.web.algorithm.recursive_single_thread;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class FileSearcher implements Searcher {

	private SearchCriteria criteria;

	public FileSearcher(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Collection<File> search(File file) {
		if (file.isDirectory()) {
			return new DirectorySearcher(criteria).search(file);
		}
		return new ConcreteFileSearcher(criteria).search(file);
	}
}
