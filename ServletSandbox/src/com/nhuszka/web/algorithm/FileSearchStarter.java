package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.database.SQLiteDBInterface;

public abstract class FileSearchStarter {
	
	public Collection<File> performSearchWithPersist(SearchCriteria searchCriteria) {
		new SQLiteDBInterface().saveSearchHistory(searchCriteria);
		return run(searchCriteria);
	}
	
	protected abstract Collection<File> run(SearchCriteria searchCriteria);
}
