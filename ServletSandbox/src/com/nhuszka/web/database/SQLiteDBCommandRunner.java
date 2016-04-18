package com.nhuszka.web.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.JDBC;

import com.nhuszka.web.database.command.SQLiteDBCommand;
import com.nhuszka.web.properties.PropertyReader;

public class SQLiteDBCommandRunner {

	private static final String SQLITE_DB_URL_PREFIX = "jdbc:sqlite:";

	static void performDBCommand(SQLiteDBCommand command) {
		try (Connection connection = getConnection()) {
			command.perform(connection);
		} catch (SQLException ex) {
			printSQLException(ex);
		}
	}

	private static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new JDBC());

		PropertyReader properties = new PropertyReader();
		String dbURL =  SQLITE_DB_URL_PREFIX + properties.readDatabaseURL();
		return DriverManager.getConnection(dbURL);
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
