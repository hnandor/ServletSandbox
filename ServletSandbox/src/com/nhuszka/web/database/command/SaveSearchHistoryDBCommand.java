package com.nhuszka.web.database.command;

import java.sql.Connection;
import java.sql.SQLException;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class SaveSearchHistoryDBCommand implements DBCommand {

	private SearchCriteria searchCriteria;

	public SaveSearchHistoryDBCommand(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public void perform(Connection connection) throws SQLException {
		// TODO
	}
}
