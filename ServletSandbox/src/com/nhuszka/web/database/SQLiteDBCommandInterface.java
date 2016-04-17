package com.nhuszka.web.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.JDBC;

import com.nhuszka.web.database.command.DBCommand;

public class SQLiteDBCommandInterface {

	private static final String DB_PATH = "d:/Prog/sqlite/servletSandbox.db";
	private static final String SQLITE_DB_URL = "jdbc:sqlite:" + DB_PATH;

	static void performDBCommand(DBCommand command) {
		try {
			DriverManager.registerDriver(new JDBC());
			try (Connection connection = DriverManager.getConnection(SQLITE_DB_URL)) {
				command.perform(connection);
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				final SQLException sqlException = (SQLException) e;
				System.err.println("SQLState: " + sqlException.getSQLState());
				System.err.println("Error Code: " + sqlException.getErrorCode());
				System.err.println("Message: " + e.getMessage());

				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
