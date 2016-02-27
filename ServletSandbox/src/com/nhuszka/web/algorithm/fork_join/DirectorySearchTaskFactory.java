package com.nhuszka.web.algorithm.fork_join;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class DirectorySearchTaskFactory {

	private final FilesWithLogs filesWithLogs;
	private final File file;
	private final SearchCriteria criteria;

	public DirectorySearchTaskFactory(FilesWithLogs filesWithLogs, File file, SearchCriteria criteria) {
		this.filesWithLogs = filesWithLogs;
		this.file = file;
		this.criteria = criteria;
	}

	public Collection<SearchFileTask> createSubTasks() {
		Collection<SearchFileTask> subTasks = new ArrayList<>();
		for (File subFile : getSubFiles(file)) {
			subTasks.add(new SearchFileTask(filesWithLogs, subFile, criteria));
		}
		for (File dir : getSubDirectories(file)) {
			subTasks.add(new SearchFileTask(filesWithLogs, dir, criteria));
		}
		return subTasks;
	}

	private Collection<File> getSubFiles(File file) {
		return getSubElements(file, createFileFilter());
	}

	private Collection<File> getSubDirectories(File file) {
		return getSubElements(file, item -> item.isDirectory());
	}

	private Collection<File> getSubElements(File file, FileFilter filtering) {
		return Arrays.asList(file.listFiles(filtering));
	}

	private FileFilter createFileFilter() {
		return file -> !file.isDirectory() && file.getName().endsWith(criteria.getExtension());
	}
}
