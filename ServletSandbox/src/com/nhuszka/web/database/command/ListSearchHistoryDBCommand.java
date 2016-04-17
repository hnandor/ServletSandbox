package com.nhuszka.web.database.command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListSearchHistoryDBCommand implements DBCommand {
	
	private static final String LIST_ALL_SEARCH_HISTORY = "SELECT * FROM search_history";

	@Override
	public void perform(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(LIST_ALL_SEARCH_HISTORY);
		while (resultSet.next()) {
			printSearchHistoryRecord(resultSet);
		}
		resultSet.close();
	}

	private void printSearchHistoryRecord(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String keyyword = rs.getString("keyword");
		String directoryFile = rs.getString("directory_file");
		String extension = rs.getString("extension");
		String algorithm = rs.getString("algorithm");

		String record = String.format(
				"id: %d, keyword: %s, directory_file: %s, extension: %s, algorithm: %s",
				id, keyyword, directoryFile, extension, algorithm);
		System.out.println(record);
	}
}
