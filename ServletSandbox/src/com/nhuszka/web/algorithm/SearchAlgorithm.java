package com.nhuszka.web.algorithm;

import com.nhuszka.web.exception.IllegalServletParameterException;

public enum SearchAlgorithm {
	
	
	SINGLE_THREAD("Single thread"),
	PARALLEL("Parallel"),
	FORK_JOIN("Fork/join");
	
	private static String ILLEGAL_ALGORITHM = "Illegal algorithm!";
	
	private final String description;
	
	private SearchAlgorithm(String description) {
		this.description = description;
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
}
