package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public interface FileSearchStarter {
	
	Collection<File> run(SearchCriteria searchCriteria);
}
