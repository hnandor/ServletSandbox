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
		SearchCriteria criteria = convertToSearchCriteria(searchBean);
		SearchAlgorithm algorithm = SearchAlgorithm.valueOf(searchBean.getAlgorithmName());
		
		return listFilePaths(algorithm, criteria);
	}
	
	private SearchCriteria convertToSearchCriteria(SearchBean searchBean) {
		SearchCriteria criteria = new SearchCriteria(
				searchBean.getDirectoryFile(), searchBean.getKeyword(), searchBean.getExtension());
		return criteria;
	}

	private Collection<String> listFilePaths(SearchAlgorithm algorithm, SearchCriteria criteria) {
		final Function<? super File, ? extends String> filePathMapper = (file) -> file.getAbsolutePath();
		return algorithm.run(criteria)
				.stream()
				.map(filePathMapper)
				.collect(Collectors.toList());
	}
}
