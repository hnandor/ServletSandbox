package com.nhuszka.web.database;

import com.nhuszka.web.algorithm.shared.SearchCriteria;
import com.nhuszka.web.database.command.ListSearchHistoryDBCommand;
import com.nhuszka.web.database.command.SaveSearchHistoryDBCommand;

public class DatabaseOperationService {
		
	public static void saveSearchHistory(SearchCriteria searchCriteria) {
		SQLiteDBCommandRunner.performDBCommand(new SaveSearchHistoryDBCommand(searchCriteria));
		listSearchHistory();
	}
	
	public static void listSearchHistory() {
		SQLiteDBCommandRunner.performDBCommand(new ListSearchHistoryDBCommand());
	}
}
