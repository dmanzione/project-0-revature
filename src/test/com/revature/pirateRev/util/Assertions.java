package com.revature.pirateRev.util;

import com.revature.pirateRev.exceptions.AssertException;

public class Assertions<T> {

	public static void equals(double a, double b) {

		if (a != b)
			throw new AssertException("Test failed!");

	}

	public static void equals(int a, int b) {

		if (a != b)
			throw new AssertException("Test failed!");

	}

	public static boolean willThrow(Class<? extends Exception> exceptionType, Runnable testMethod) {

		try {

			testMethod.run();
			// if control reaches the return statement
			// then we know the exception wasn't thrown
			// because no exception was thrown
			return false;

		} catch (Exception ex) {

			// if the exception thrown does no equal
			// the expected exception, return false
			if (!ex.getClass().equals(exceptionType))
				return false;
			else
				return true;

		}

	}

}