package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.web.algorithm.parallel.GenericFileSearcherTask;
import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

class StartParallelFileSearcher implements FileSearchStarter {

	@Override
	public Collection<File> run(SearchCriteria searchCriteria) {
		long startTime = System.currentTimeMillis();
		final FilesWithLogs filesWithLogs = FilesWithLogs.createSharedFileContainer();
		final CyclicBarrier barrier = new CyclicBarrier(2, getReadFilesAndLogsAction(filesWithLogs, startTime));

		final String directory = searchCriteria.getDirectory();
		log("Start MAIN thread to search in " + directory + "\n", filesWithLogs);

		File rootFile = new File(directory);
		GenericFileSearcherTask rootSearcher = new GenericFileSearcherTask(
				rootFile, filesWithLogs, barrier, searchCriteria);
		rootSearcher.search();

		return filesWithLogs.getFiles();
	}

	private Runnable getReadFilesAndLogsAction(final FilesWithLogs filesWithLogs, long startTime) {
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

	public static void log(String str, FilesWithLogs files) {
		files.log(str);
	}
}
