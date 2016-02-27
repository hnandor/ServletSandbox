package com.nhuszka.web.algorithm.recursive_single_thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class ConcreteFileSearcher implements Searcher {

	private SearchCriteria criteria;

	public ConcreteFileSearcher(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Collection<File> search(File file) {
		Collection<File> result = new ArrayList<>();
		if (isMatch(file)) {
			result.add(file);
		}
		return result;
	}

	private boolean isMatch(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader
					.lines()
					.anyMatch(item -> item.contains(criteria.getKeyword()));
		} catch (FileNotFoundException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		} catch (IOException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		}
		return false;
	}
}
