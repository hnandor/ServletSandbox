package com.nhuszka.web.algorithm.parallel;

import java.io.File;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class GenericFileSearcherTask extends AbstractFileSearcherTask {

	public GenericFileSearcherTask(File file, FilesWithLogs filesWithLogs,
			CyclicBarrier barrier, SearchCriteria criteria) {
		super(file, filesWithLogs, barrier, criteria);
	}

	@Override
	public void search() {
		Runnable searchTask = file.isDirectory()
				? new DirectorySearcherParallelTask(file, filesWithLogs, barrier, criteria)
				: new FileReaderSingleTask(file, filesWithLogs, barrier, criteria);

				new Thread(searchTask).start();
				signDone();
	}
}
