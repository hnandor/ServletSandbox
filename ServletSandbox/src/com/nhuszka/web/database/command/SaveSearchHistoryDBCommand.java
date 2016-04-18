package com.nhuszka.web.database.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class SaveSearchHistoryDBCommand implements SQLiteDBCommand {

	private static final String SAVE_SEARCH_HISTORY = "INSERT INTO search_history(keyword, directory_file, extension, algorithm) VALUES (?, ?, ?, ?)";

	private SearchCriteria searchCriteria;

	public SaveSearchHistoryDBCommand(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@Override
	public void perform(Connection connection) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(SAVE_SEARCH_HISTORY)) {
			stmt.setString(1, searchCriteria.getKeyword());
			stmt.setString(2, searchCriteria.getRoot());
			stmt.setString(3, searchCriteria.getExtension());
			stmt.setString(4, searchCriteria.getAlgorithm().getKey());

			stmt.executeUpdate();
		}
	}
}
