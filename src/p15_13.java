import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

import net.mindview.util.Generator;
import generics.coffee.*;
import generics.*;

public class p15_13 {
	public static <T> Collection<T> fill(Collection<T> c, Generator<T> gen, int n){
		for(int i = 0; i < n ; i++){
			c.add(gen.next());
		}
		return c;
	}
	
	public static <T> List<T> fill(List<T> c, Generator<T> gen, int n){
		System.out.println("List");
		for(int i = 0; i < n ; i++){
			c.add(gen.next());
		}
		return c;
	}
	
/*	public static <T> LinkedList<T> fill(LinkedList<T> c, Generator<T> gen, int n){
		System.out.println("LinkedList");
		for(int i = 0; i < n ; i++){
			c.add(gen.next());
		}
		return c;
	}*/
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<Integer> fnum = fill(new LinkedList<Integer>(), new Fibonacci(), 10);
		System.out.println(fnum);
	}

}
