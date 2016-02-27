package com.nhuszka.web.algorithm.parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class FileReaderSingleTask extends AbstractFileSearcherTask implements Runnable {

	public FileReaderSingleTask(File file, FilesWithLogs filesWithLogs,
			CyclicBarrier barrier, SearchCriteria criteria) {
		super(file, filesWithLogs, barrier, criteria);
	}

	@Override
	public void search() {
		if (isMatch(file)) {
			log("Added: " + file.getAbsolutePath(), filesWithLogs);
			filesWithLogs.add(file);
		} else {
			log("Skipped: " + file.getAbsolutePath(), filesWithLogs);
		}
		signDone();
	}

	private boolean isMatch(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader
					.lines()
					.anyMatch(item -> item.contains(criteria.getKeyword()));
		} catch (FileNotFoundException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		} catch (IOException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		}
		return false;
	}
}
