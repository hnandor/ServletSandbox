package com.nhuszka.web.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private static final String SYSTEM_PROPERTIES_FILE_NAME = "system.properties";
	private static final String DB_URL_KEY = "db_url";

	public String readDatabaseURL() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(SYSTEM_PROPERTIES_FILE_NAME);

		Properties properties = new Properties();
		try {
			properties.load(input);
			return (String) properties.get(DB_URL_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
