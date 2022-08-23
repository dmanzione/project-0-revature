package com.revature.pirateRev.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.revature.pirateRev.dao.PirateDAOTest;
import com.revature.pirateRev.util.Test;

public class TestRunner {

	public static void main(String[] args) {
		
		Class<PirateDAOTest> obj = PirateDAOTest.class;
		System.out.println("Running tests...");

		for (Method method : obj.getDeclaredMethods()) {
		
			if (method.isAnnotationPresent(Test.class)) {
				try {
					System.out.println("Testing method: " + method.getName());
					method.invoke(obj.getDeclaredConstructor().newInstance());
					System.out.println("Test passed");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (InvocationTargetException e) {

					System.out.println("Test failed" + e.getTargetException());

				} catch (InstantiationException e) {

					e.printStackTrace();
				} catch (NoSuchMethodException e) {

					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
