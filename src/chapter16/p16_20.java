package chapter16;

import java.util.Arrays;

public class p16_20 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		int[][] a = {{1, 2, 3}, {4, 5, 6}};
		int[][] b = {{1, 2, 3}, {4, 5, 6}};
		System.out.println(Arrays.deepEquals(a, b));
		
		Arrays.sort(a);
	}

}
