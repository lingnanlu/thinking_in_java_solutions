package chapter16;

import java.util.Arrays;
import java.util.Random;

public class p16_3 {

	public static Double[][] create(int x, int y, double low, double high){
		Random rand = new Random(47);
		Double[][] result = new Double[x][];
		for(int i = 0; i < result.length; i++){
			result[i] = new Double[y];
			for(int j = 0; j < result[i].length; j++){
				result[i][j] = low + rand.nextDouble() * (high - low);
			}
		}
		
		return result;
	}
	
	public static void print(Double[][] a){
		System.out.println(Arrays.deepToString(a));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		print(create(5, 3, 8.0, 12.0));
	}

}
