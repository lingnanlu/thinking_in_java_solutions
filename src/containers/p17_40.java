package containers;
import java.util.*;

import io.lingnanlu.github.*;
import static io.lingnanlu.github.Print.*;

class DoubleStringA implements Comparable<DoubleStringA>{

	private String first;
	private String second;
	
	public DoubleStringA(Generator<String> gen){
		first = gen.next();
		second = gen.next();
	}
	
	public DoubleStringA(String first, String second){
		this.first = first;
		this.second = second;
	}
	@Override
	public int compareTo(DoubleStringA o) {
		// TODO 自动生成的方法存根
		return first.compareTo(o.first);
	}
	
	public String toString(){
		return "(" + first + "," + second + ")";
	}
	
	public int hashCode(){
		int result = 37;
		result = 37 * result + first.hashCode();
		result = 37 * result + second.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		return obj instanceof DoubleStringA && 
				((DoubleStringA)obj).first == first &&
				((DoubleStringA)obj).second == second;
	}
	

}
public class p17_40 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<DoubleStringA> listA = new ArrayList<DoubleStringA>();
		Generator<String> gen = new RandomGenerator.String();
		for(int i = 0; i < 10; i++){
			listA.add(new DoubleStringA(gen));
		}
		
		print(listA);
		
		Collections.sort(listA);
		
		print(listA);
		
		DoubleStringA key = new DoubleStringA("TcQrGse", "GZMmJMR");
		
		print(Collections.binarySearch(listA, key));
		
	
		
		Set<DoubleStringA> dStringSet = new HashSet<DoubleStringA>();
		for(int i = 0; i < 5; i++){
			dStringSet.add(new DoubleStringA("hehe", "xixi"));
		}
		
		print(dStringSet);
	}

}
