package com.nhuszka.web.database.command;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBCommand {
	
	void perform(Connection connection) throws SQLException;
}
