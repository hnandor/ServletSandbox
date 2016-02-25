package com.nhuszka.web.algorithm;

import java.io.File;
import java.util.Collection;

import com.nhuszka.web.algorithm.recursive_single_thread.FileSearcher;

public class StartRecursiveSingleThreadFileSearcher {

	public static String SEARCH_TEXT;
	public static String SEARCH_EXTENSION;
	private static String ROOT_DIRECTORY;

	public static Collection<File> run(String root, String searchText, String extension) {
		ROOT_DIRECTORY = root;
		SEARCH_TEXT = searchText;
		SEARCH_EXTENSION = extension;

		long startTime = System.currentTimeMillis();
		Collection<File> files = new FileSearcher().search(new File(ROOT_DIRECTORY));
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));

		return files;
	}
}
