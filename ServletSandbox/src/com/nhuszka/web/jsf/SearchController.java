package com.nhuszka.web.jsf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nhuszka.web.algorithm.SearchAlgorithm;
import com.nhuszka.web.jsf.action.SearchTask;
import com.nhuszka.web.jsf.bean.SearchBean;

@ManagedBean(name = "searchController")
@SessionScoped
public class SearchController {

	// key: option label
	// value: option value
	public Map<String, String> getSearchAlgorithmSelectorModel() {
		Map<String, String> algorithms = new HashMap<>();

		for (SearchAlgorithm algorithm : SearchAlgorithm.values()) {
			algorithms.put(algorithm.getDescription(), algorithm.name());
		}

		return algorithms;
	}
	
	// CURRENTLY NOT USED: but we could get the results this way
	public Collection<String> computeSearchResults(SearchBean searchBean) {
		return new SearchTask().computeSearchResults(searchBean);
	}
}
