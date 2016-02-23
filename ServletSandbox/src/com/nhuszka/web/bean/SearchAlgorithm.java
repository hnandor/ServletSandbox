package com.nhuszka.web.bean;

public enum SearchAlgorithm {
	
	SINGLE_THREAD("Single thread"),
	PARALLEL("Parallel"),
	FORK_JOIN("Fork/join");
	
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
}
