package com.revature.pirateRev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://pirate-supply-store.cbvhz1czalox.us-east-1.rds.amazonaws.com:5432/postgres",
					"dmanzione", "12345678");

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
