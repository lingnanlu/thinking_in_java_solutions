package chapter16;

import java.util.Arrays;

public class p16_2 {

	public static BerylliumSphere[] test(int n){
		return new BerylliumSphere[n];
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		BerylliumSphere[] a = test(5);
		for(int i = 0; i < a.length; i++){
			a[i] = new BerylliumSphere();
		}
		System.out.println(Arrays.toString(a));
	}

}
