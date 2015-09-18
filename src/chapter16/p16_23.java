package chapter16;
import java.util.Arrays;
import java.util.Collections;

import io.lingnanlu.github.*;

public class p16_23 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		Integer[] a = Generated.array(new Integer[10], gen);
		
		System.out.println(Arrays.toString(a));
		
		Arrays.sort(a);
		
		System.out.println(Arrays.toString(a));
		
		Arrays.sort(a, Collections.reverseOrder());
		
		System.out.println(Arrays.toString(a));
	}

}
