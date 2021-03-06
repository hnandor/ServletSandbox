package com.nhuszka.web.algorithm.fork_join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.RecursiveAction;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class SearchFileTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private final FilesWithLogs filesWithLogs;
	private final File file;
	private final SearchCriteria criteria;

	public SearchFileTask(FilesWithLogs filesWithLogs, File file, SearchCriteria criteria) {
		this.filesWithLogs = filesWithLogs;
		this.file = file;
		this.criteria = criteria;
	}

	@Override
	protected void compute() {
		if (file.isFile()) {
			if (isMatch(file)) {
				filesWithLogs.add(file);
			}
		} else { // directory
			DirectorySearchTaskFactory subTaskFactory = new DirectorySearchTaskFactory(filesWithLogs, file, criteria);
			invokeAll(subTaskFactory.createSubTasks());
		}
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