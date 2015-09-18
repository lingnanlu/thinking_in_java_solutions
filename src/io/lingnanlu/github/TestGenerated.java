package io.lingnanlu.github;

import java.util.Arrays;

public class TestGenerated {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Integer[] a = {9, 8, 7, 6};
		System.out.println(Arrays.toString(a));
		a = Generated.array(a, new CountingGenerator.Integer());
		System.out.println(Arrays.toString(a));
		
		Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 10);
		
		System.out.println(Arrays.toString(b));
	}

}
