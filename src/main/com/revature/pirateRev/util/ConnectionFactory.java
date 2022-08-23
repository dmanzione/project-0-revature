package com.revature.pirateRev.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.pirateRev.util.CaptainsLogger.LogLevel;

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
		Properties properties = new Properties();
		try {
			properties.setProperty("username", "dmanzione");
			properties.setProperty("password", "12345678");
			properties.setProperty("url",
					"jdbc:postgresql://pirate-supply-store.cbvhz1czalox.us-east-1.rds.amazonaws.com:5432/postgres");
			properties.load(new FileReader("/Users/donato/Documents/workspace-spring-tool-suite-4-4.15.1.RELEASE/PirateSupply/resources/database.properties"));
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
