package containers;

import java.util.*;
import static io.lingnanlu.github.Print.*;

class SlowSet<E> extends AbstractSet<E>{
	
	List<E> list = new ArrayList<E>();
	
	
	public boolean add(E element){
		if (contains(element)){
			return false;
		} else {
			list.add(element);
			return true;
		}
	}
	@Override
	public Iterator<E> iterator() {
		// TODO 自动生成的方法存根
		return new Iterator<E>(){
			int index = -1;
			@Override
			public boolean hasNext() {
				// TODO 自动生成的方法存根
				return index < list.size() - 1;
			}

			@Override
			public E next() {
				// TODO 自动生成的方法存根
				E element = list.get(++index);
				return element;
			}
			
			public void remove(){
				list.remove(index--);
				
			}
			
		};
	}

	
	@Override
	public int size() {
		// TODO 自动生成的方法存根
		return list.size();
	}
	
}
public class p17_18 {
	public static void main(String[] args){
		SlowSet<String> ss = new SlowSet<String>();
		ss.add("hi");
		print(ss);
		ss.add("there");
		print(ss);		
		List<String> list = Arrays.asList("you", "cutie", "pie");
		ss.addAll(list);
		print(ss);
		print("ss.size() = " + ss.size());
		print("ss.contains(\"you\"): " + ss.contains("you"));
		print("ss.contains(\"me\"): " + ss.contains("me"));	
		print("ss.containsAll(list): " + ss.containsAll(list));
		SlowSet<String> ss2 = new SlowSet<String>();
		print("ss2 = " + ss2);
		print("ss.containsAll(ss2): " + ss.containsAll(ss2));
		print("ss2.containAll(ss): " + ss2.containsAll(ss));
		ss2.add("you");
		ss2.add("cutie");
		ss.removeAll(ss2);
		print("ss = " + ss);
		print("ss.hashCode() = " + ss.hashCode());
		List<String> list2 = Arrays.asList("hi", "there", "pie");
		ss2.remove("you");
		print(ss2);
		print("ss2.isEmpty(): " + ss2.isEmpty());
		ss2.clear();
		print("ss2.isEmpty(): " + ss2.isEmpty());
		ss2.addAll(list2);
		print("ss2 = " + ss2);
		print("ss.equals(ss2): " + ss.equals(ss2));
		String[] sa = new String[3];
		print("ss.toArray(sa): " + ss.toArray(sa));
		for(int i = 0; i < sa.length; i++) printnb(sa[i] + " " );
	}
}
