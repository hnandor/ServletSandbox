package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nhuszka.web.algorithm.fork_join.SearchFileTask;
import com.nhuszka.web.algorithm.shared.FilesWithLogs;

public class StartForkJoinMultiThreadFileSearcher {

	public static String SEARCH_TEXT;
	public static String SEARCH_EXTENSION;
	private static String ROOT_DIRECTORY;

	public static Collection<File> run(String root, String searchText, String extension) {
		ROOT_DIRECTORY = root;
		SEARCH_TEXT = searchText;
		SEARCH_EXTENSION = extension;

		long startTime = System.currentTimeMillis();

		FilesWithLogs filesWithLogs = createSharedFileContainer();

		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new SearchFileTask(filesWithLogs, new File(ROOT_DIRECTORY)));

		for (File file : filesWithLogs.getFiles()) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));

		return filesWithLogs.getFiles();
	}

	private static FilesWithLogs createSharedFileContainer() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		return new FilesWithLogs(new ArrayList<>(), lock);
	}
}