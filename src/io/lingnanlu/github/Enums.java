package io.lingnanlu.github;

import java.util.Random;

public class Enums {
	private static Random rand = new Random(47);
	public static <T extends Enum<T>> T random(Class<T> cs){
		return random(cs.getEnumConstants());
	}
	
	public static <T> T random(T[] values){
		return values[rand.nextInt(values.length)];
	}
}
