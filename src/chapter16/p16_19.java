package chapter16;

import java.util.Arrays;

public class p16_19 {
	public int a;
	public p16_19(int a){
		this.a = a;
	}
	
	public String toString(){
		return a + "";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		return this.a == ((p16_19)obj).a;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p16_19[] a = new p16_19[5];
		p16_19[] b = new p16_19[5];
		
		Arrays.fill(a, new p16_19(4));
		Arrays.fill(b, new p16_19(4));
		
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		
		System.out.println(Arrays.equals(a, b));
	}

}
