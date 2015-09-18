import java.util.Iterator;

import generics.*;
import generics.coffee.Coffee;
public class p15_7 implements Iterable<Integer>{
	private int n;
	public p15_7(int count){n = count;}
	private Fibonacci fi = new Fibonacci();
	@Override
	public Iterator<Integer> iterator() {
		// TODO 自动生成的方法存根
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				// TODO 自动生成的方法存根
				return n > 0;
			}

			@Override
			public Integer next() {
				// TODO 自动生成的方法存根
				n--;
				return fi.next();
			}
			
		};
	}
	
	public static void main(String[] args){
		for(int i : new p15_7(10)){
			System.out.print(i + " ");
		}
	}
}
