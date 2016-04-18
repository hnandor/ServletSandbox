package com.nhuszka.web.database.command;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLiteDBCommand {
	
	void perform(Connection connection) throws SQLException;
}
