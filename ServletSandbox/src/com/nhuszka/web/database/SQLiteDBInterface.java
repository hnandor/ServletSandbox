package com.nhuszka.web.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nhuszka.web.algorithm.shared.SearchCriteria;

public class SQLiteDBInterface {

	private static final String DB_PATH = "d:/Prog/sqlite/servletSandbox.db";

	public void saveSearchHistory(SearchCriteria searchCriteris) {
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:" + DB_PATH;
			Connection conn = DriverManager.getConnection(dbURL);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String sql = "SELECT * FROM search_history";

				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
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
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException se) {
				}
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
