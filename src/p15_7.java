import java.util.Iterator;

import generics.*;
import generics.coffee.Coffee;
public class p15_7 implements Iterable<Integer>{
	private int n;
	public p15_7(int count){n = count;}
	private Fibonacci fi = new Fibonacci();
	@Override
	public Iterator<Integer> iterator() {
		// TODO �Զ����ɵķ������
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				// TODO �Զ����ɵķ������
				return n > 0;
			}

			@Override
			public Integer next() {
				// TODO �Զ����ɵķ������
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
