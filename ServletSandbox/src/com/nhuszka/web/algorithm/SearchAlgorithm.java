package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.exception.IllegalServletParameterException;

public enum SearchAlgorithm {

	SINGLE_THREAD("Single thread", new StartRecursiveSingleThreadFileSearcher()),
	PARALLEL("Parallel", new StartParallelFileSearcher()),
	FORK_JOIN("Fork/join", new StartForkJoinMultiThreadFileSearcher());

	private static String ILLEGAL_ALGORITHM = "Illegal algorithm!";

	private final String description;
	private final FileSearchStarter searchStarter;

	private SearchAlgorithm(String description, FileSearchStarter searchStarter) {
		this.description = description;
		this.searchStarter = searchStarter;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Algorithm[" + description + "]";
	}

	public static void checkExistingAlgorithm(String algorithmName) throws IllegalServletParameterException {
		if (algorithmName == null || algorithmName.isEmpty() || !existsAlgorithm(algorithmName)) {
			throw new IllegalServletParameterException(ILLEGAL_ALGORITHM);
		}
	}

	private static boolean existsAlgorithm(String algorithmName) {
		for (SearchAlgorithm algorithm : SearchAlgorithm.values()) {
			if (algorithm.name().equals(algorithmName)) {
				return true;
			}
		}
		return false;
	}
	
	public Collection<File> run(SearchCriteria searchCriteria) {
		return searchStarter.run(searchCriteria);
	}
}
