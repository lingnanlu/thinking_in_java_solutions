import java.util.*;

//import java.util.Iterator;

public class p11_11 {
	
	
	public static void print(Collection collection){
		Iterator it = collection.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args){
		ArrayList<Integer> al = 
				new ArrayList<Integer>(Arrays.asList(1, 2, 3));
			LinkedList<Character> ll =
				new LinkedList<Character>(Arrays.asList('a', 'b', 'c'));	
			HashSet<Float> hs = 
				new HashSet<Float>(Arrays.asList(1.1f, 2.2f, 3.3f));
			TreeSet<Double> ts =
				new TreeSet<Double>(Arrays.asList(1.11, 2.22, 3.33));
			LinkedHashSet<Integer> lhs =
				new LinkedHashSet<Integer>(Arrays.asList(11, 22, 33));
			
		print(al);
		print(ll);
		print(hs);
		print(ts);
		print(lhs);
	}
}
