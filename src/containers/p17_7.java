package containers;

import java.util.*;

import io.lingnanlu.github.*;;

public class p17_7 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<String> alist = new ArrayList<String>(Countries.names(5));
		List<String> llist = new LinkedList<String>(Countries.names(5));
		
		Iterator<String> it = alist.iterator();
		
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		
		it = llist.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		it = llist.iterator();
		
		ListIterator<String> reverseiter = alist.listIterator(alist.size());
		
		while(reverseiter.hasPrevious() && it.hasNext()){
			reverseiter.add(it.next());
			reverseiter.previous();
			reverseiter.previous();
		}
		
		System.out.println(alist);
		
		
		
		
		
		
	}

}
