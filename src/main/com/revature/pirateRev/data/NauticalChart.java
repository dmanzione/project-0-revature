package com.revature.pirateRev.data;

import java.util.HashMap;
import java.util.Map;

public class NauticalChart {

	public static Map<String, String> map = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{

			put("Captain Branch", "Fleur Marie: 44° 42' 59.22\" N -75° 28' 34.68\" W");
			put("First Mate Branch", "Dawn: 42° 25' 15.06\" N -81° 21' 30.1212\" W");
			put("Quartermaster Branch", "Valentine: 41° 55' 7.32\" N -81° 54' 47.16\" W");
			put("Sailing Master Branch", "Two Fannies: 41° 33' 51.0012\" N -81° 55' 16.7988\" W");
			put("Gunner Branch", "Trade Wind: 42° 25' 8.4\" N -80° 12' 3.6\" W");
			put("Powder Monkey Branch", "Elk: 44° 14' 37.7988\" N -76° 4' 50.9988\" W");
			put("Boatswain Branch", "Kinghorn: 44° 22' 36.3612\" N -75° 55' 50.2788\" W");
			put("Surgeon Branch", "Land Tortoise: 43° 26' 24.72\" -73° 41' 33.72\" W");
			put("Cook Branch", "Milan: 43° 22' 19.8084\" N -78° 11' 28.86\" W");

		}
	};

	public static String get(String name) {

		return map.get(name);
	}

}
