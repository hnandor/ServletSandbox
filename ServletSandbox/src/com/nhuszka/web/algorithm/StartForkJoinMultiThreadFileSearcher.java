package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

import com.nhuszka.web.algorithm.fork_join.SearchFileTask;
import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

class StartForkJoinMultiThreadFileSearcher implements FileSearchStarter {

	@Override
	public Collection<File> run(SearchCriteria searchCriteria) {
		long startTime = System.currentTimeMillis();

		FilesWithLogs filesWithLogs = FilesWithLogs.createSharedFileContainer();

		ForkJoinPool pool = new ForkJoinPool();
		SearchFileTask searchTask = new SearchFileTask(
				filesWithLogs, new File(searchCriteria.getRoot()), searchCriteria);
		pool.invoke(searchTask);

		for (File file : filesWithLogs.getFiles()) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));

		return filesWithLogs.getFiles();
	}
}