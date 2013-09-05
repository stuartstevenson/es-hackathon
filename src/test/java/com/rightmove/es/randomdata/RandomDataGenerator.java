package com.rightmove.es.randomdata;

import java.util.Random;

/**
 * Generator for random values.
 */
public class RandomDataGenerator {
	private static final char[]	STRING_CHARS	= new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};

	/**
	 * Random generator in use.
	 */
	public final Random			r;

	/**
	 * Creates a new generator without a specific seed.
	 */
	public RandomDataGenerator() {
		r = new Random();
	}

	/**
	 * Creates a new generator with a specified seed.
	 * 
	 * @param seed
	 */
	public RandomDataGenerator(final long seed) {
		r = new Random(seed);
	}

	/**
	 * Generates a random int value. See {@link Random#nextInt(int)};
	 * 
	 * @param max Upper bound for random value (exclusive).
	 * @return Random number.
	 */
	public int getInt(final int max) {
		return r.nextInt(max);
	}

	/**
	 * Generates a random string with a specified minimum and maximum length.
	 * 
	 * @param minStringLength Min length.
	 * @param maxStringLength Max length.
	 * @return Random string.
	 */
	public String getString(final int minStringLength, final int maxStringLength) {
		final int stringLength = minStringLength + r.nextInt(maxStringLength);
		final StringBuilder string = new StringBuilder();
		for (int i = 0; i < stringLength; i++) {
			string.append(STRING_CHARS[r.nextInt(STRING_CHARS.length)]);
		}
		return string.toString();
	}
}
