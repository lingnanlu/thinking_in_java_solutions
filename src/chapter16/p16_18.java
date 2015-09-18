package chapter16;

import java.util.Arrays;

public class p16_18 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		BerylliumSphere[] a = new BerylliumSphere[10];
		BerylliumSphere[] b = new BerylliumSphere[10];
		
		Arrays.fill(a, new BerylliumSphere());
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		
		System.arraycopy(a, 0, b, 0, a.length);
		System.out.println(Arrays.toString(b));

		
		
	}

}
