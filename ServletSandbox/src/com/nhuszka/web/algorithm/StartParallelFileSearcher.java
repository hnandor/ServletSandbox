package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nhuszka.web.algorithm.parallel.GenericFileSearcherTask;
import com.nhuszka.web.algorithm.shared.FilesWithLogs;

public class StartParallelFileSearcher {

	public static String SEARCH_TEXT;
	public static String SEARCH_EXTENSION;
	private static String ROOT_DIRECTORY;
	static long startTime;

	public static void log(String str, FilesWithLogs files) {
		files.log(str);
	}

	public static Collection<File> run(String root, String searchText, String extension) {
		ROOT_DIRECTORY = root;
		SEARCH_TEXT = searchText;
		SEARCH_EXTENSION = extension;

		startTime = System.currentTimeMillis();
		final FilesWithLogs filesWithLogs = createSharedFileContainer();
		final CyclicBarrier barrier = new CyclicBarrier(2, getReadFilesAndLogsAction(filesWithLogs));

		log("Start MAIN thread to search in " + ROOT_DIRECTORY + "\n", filesWithLogs);

		File rootFile = new File(ROOT_DIRECTORY);
		GenericFileSearcherTask rootSearcher = new GenericFileSearcherTask(rootFile, filesWithLogs, barrier);
		rootSearcher.search();

		return filesWithLogs.getFiles();
	}

	private static FilesWithLogs createSharedFileContainer() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		return new FilesWithLogs(new ArrayList<>(), lock);
	}

	private static Runnable getReadFilesAndLogsAction(final FilesWithLogs filesWithLogs) {
		return () -> {
			log("\nWohoo! MAIN thread gets everything!\n", filesWithLogs);

			for (String log : filesWithLogs.getLogs()) {
				System.out.println(log);
			}


			System.out.println("--- Result files start ---");
			for (File file : filesWithLogs.getFiles()) {
				System.out.println(file.getAbsolutePath());
			}
			System.out.println("--- Result files end ---");

			System.out.println("\nMAIN thread exits...");

			System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));
		};
	}
}
