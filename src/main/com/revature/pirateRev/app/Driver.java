package com.revature.pirateRev.app;

import com.revature.pirateRev.ui.Menu;
import com.revature.pirateRev.utils.CaptainsLogger;
import com.revature.pirateRev.utils.CaptainsLogger.LogLevel;

public class Driver {
	private static CaptainsLogger logger = CaptainsLogger.getLogger();

	public static void main(String[] args) {
		logger.log(LogLevel.INFO, "Application starting up...");

		Menu.open();

	}

}
