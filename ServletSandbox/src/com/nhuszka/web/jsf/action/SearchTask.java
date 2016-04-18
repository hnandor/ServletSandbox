package com.nhuszka.web.jsf.action;

import java.io.File;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.jsf.bean.SearchBean;

public class SearchTask {
	
	public Collection<String> computeSearchResults(SearchBean searchBean) {
		SearchAlgorithm algorithm = SearchAlgorithm.valueOf(searchBean.getAlgorithmName());
		SearchCriteria criteria = convertToSearchCriteria(searchBean, algorithm);
		
		return listFilePaths(criteria);
	}
	
	private SearchCriteria convertToSearchCriteria(SearchBean searchBean, SearchAlgorithm algorithm) {
		return new SearchCriteria(searchBean.getDirectoryFile(), searchBean.getKeyword(), searchBean.getExtension(), algorithm);
	}

	private Collection<String> listFilePaths(SearchCriteria criteria) {
		final Function<? super File, ? extends String> filePathMapper = (file) -> file.getAbsolutePath();
		return criteria.getAlgorithm().performSearchWithPersist(criteria)
				.stream()
				.map(filePathMapper)
				.collect(Collectors.toList());
	}
}
