import java.util.*;

public class p11_14 {
	public static void main(String[] args){
		LinkedList<Integer> linklist = new LinkedList<Integer>();
		Integer[] ints = {1, 3, 5, 7, 9};
		
		ListIterator<Integer> it;
		for(Integer i : ints){
			it = linklist.listIterator(linklist.size() / 2);
			it.add(i);
		}
		
		System.out.println(linklist);
	}
}
