package chapter16;
import java.util.Arrays;

import io.lingnanlu.github.*;

public class p16_22 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		Integer[] a = Generated.array(new Integer[10], gen);
		
		System.out.println(Arrays.toString(a));
		while(true){
			int r = gen.next();
			int location = Arrays.binarySearch(a, r);
			if(location >= 0){
				System.out.println("location of " + r + " is " + location + ", a[" + 
			location + "] = " + a[location]);
				break;
			}
		
		}
	}

}
