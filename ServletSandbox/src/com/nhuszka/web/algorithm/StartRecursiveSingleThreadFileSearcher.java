package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.recursive_single_thread.FileSearcher;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

class StartRecursiveSingleThreadFileSearcher implements FileSearchStarter {

	@Override
	public Collection<File> run(SearchCriteria searchCriteria) {
		long startTime = System.currentTimeMillis();
		Collection<File> files = new FileSearcher(searchCriteria)
				.search(new File(searchCriteria.getDirectory()));
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));

		return files;
	}
}
