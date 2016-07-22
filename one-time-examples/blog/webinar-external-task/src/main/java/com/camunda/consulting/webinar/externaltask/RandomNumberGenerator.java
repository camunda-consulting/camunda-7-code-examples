package com.camunda.consulting.webinar.externaltask;

import java.util.Random;

public class RandomNumberGenerator {

	private static Random randomGenerator = new Random();

	public static int getNumber(int max) {
			return randomGenerator.nextInt(max);
	}
}
