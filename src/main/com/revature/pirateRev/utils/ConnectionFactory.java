package com.revature.pirateRev.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.pirateRev.utils.CaptainsLogger.LogLevel;

public class ConnectionFactory {
	private static CaptainsLogger logger = CaptainsLogger.getLogger();

	private static ConnectionFactory connectionFactory;

	static {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

	}

	public Connection getConnection() {

		Connection conn = null;
		String url, username, password;
		Properties properties = new Properties();
		try {

			properties.load(new FileReader(
					"/Users/user/Desktop/eclipse-workspace/PirateSupply/resources/database.properties"));
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));

		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, ("Could not get connection to database intance: " + e.getMessage()));

		}

		return conn;
	}

	public static ConnectionFactory getInstance() {

		connectionFactory = new ConnectionFactory();
		return connectionFactory;

	}
}
