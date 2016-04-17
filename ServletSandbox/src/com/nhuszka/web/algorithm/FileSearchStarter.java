package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.database.DatabaseOperationService;

public abstract class FileSearchStarter {
	
	public Collection<File> performSearchWithPersist(SearchCriteria searchCriteria) {
		DatabaseOperationService.saveSearchHistory(searchCriteria);
		return run(searchCriteria);
	}
	
	protected abstract Collection<File> run(SearchCriteria searchCriteria);
}
