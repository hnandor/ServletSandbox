package com.nhuszka.web.algorithm.parallel;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class DirectorySearcherParallelTask extends AbstractFileSearcherTask {

	public DirectorySearcherParallelTask(File file, FilesWithLogs filesWithLogs, CyclicBarrier barrier,
			SearchCriteria criteria) {
		super(file, filesWithLogs, barrier, criteria);
	}

	@Override
	public void search() {
		Collection<File> dirs = getSubDirectories(file);
		Collection<File> subFiles = getSubFiles(file);

		int numOfSubFiles = dirs.size() + subFiles.size();
		CyclicBarrier myBarrier = new CyclicBarrier(numOfSubFiles + 1);

		log("Wait for threads in " + file.getAbsolutePath(), filesWithLogs);

		for (File dir : dirs) {
			new Thread(new DirectorySearcherParallelTask(dir, filesWithLogs, myBarrier, criteria)).start();
		}

		for (File subFile : subFiles) {
			new Thread(new FileReaderSingleTask(subFile, filesWithLogs, myBarrier, criteria)).start();
		}

		try {
			myBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		log("Sub threads are done in " + file.getAbsolutePath(), filesWithLogs);
		signDone();
	}

	private Collection<File> getSubDirectories(File file) {
		return getSubElements(file, item -> item.isDirectory());
	}

	private Collection<File> getSubFiles(File file) {
		return getSubElements(file, createFileFilter());
	}

	private Collection<File> getSubElements(File file, FileFilter filtering) {
		return Arrays.asList(file.listFiles(filtering));
	}

	private FileFilter createFileFilter() {
		return file -> !file.isDirectory() && file.getName().endsWith(criteria.getExtension());
	}
}
