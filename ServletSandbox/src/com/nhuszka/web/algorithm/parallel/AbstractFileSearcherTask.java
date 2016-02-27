package com.nhuszka.web.algorithm.parallel;

import java.io.File;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.web.algorithm.shared.FilesWithLogs;
import com.nhuszka.web.algorithm.shared.SearchCriteria;

public abstract class AbstractFileSearcherTask implements Runnable {

	protected final File file;
	protected final FilesWithLogs filesWithLogs;
	protected final CyclicBarrier barrier;
	protected SearchCriteria criteria;

	protected AbstractFileSearcherTask(File file, FilesWithLogs filesWithLogs,
			CyclicBarrier barrier, SearchCriteria criteria) {
		this.file = file;
		this.filesWithLogs = filesWithLogs;
		this.barrier = barrier;
		this.criteria = criteria;
	}

	@Override
	public void run() {
		search();
		// signDone cannot be called here!
	}

	protected void signDone() {
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public abstract void search();
	
	protected static void log(String str, FilesWithLogs files) {
		files.log(str);
	}
}
