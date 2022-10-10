package com.revature.pirateRev.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CaptainsLogger {
	public static enum LogLevel {
		INFO, DEBUG, VERBOSE, WARNING, FATAL, ERROR
	}

	private CaptainsLogger() {
	}

	public static CaptainsLogger getLogger() {
		return new CaptainsLogger();
	}

	public void log(LogLevel level, String message) {
		CaptainsLog newLog = new CaptainsLog(level, LocalDateTime.now(), message);
		writeToFile(newLog.toString());
	}

	private void writeToFile(String message) {
		try (FileWriter writer = new FileWriter("/Users/user/Desktop/eclipse-workspace/PirateSupply/resources/logs/" + LocalDateTime.now().toLocalDate() + ".log", true)) {

			writer.append(message + "\n");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private class CaptainsLog {
		LogLevel logLevel;
		LocalDateTime timestamp;
		String message;

		public CaptainsLog(LogLevel logLevel, LocalDateTime timestamp, String message) {
			super();
			this.logLevel = logLevel;
			this.timestamp = timestamp;
			this.message = message;
		}

		@Override
		public String toString() {
			int hourInt = timestamp.getHour();
			String hour = hourInt < 10 ? "0" + hourInt : "" + hourInt;
			int minuteInt = timestamp.getMinute();
			String minute = minuteInt < 10 ? "0" + minuteInt : "" + minuteInt;

			return logLevel + " - " + hour + ":" + minute + " - " + message;

		}
	}
}
