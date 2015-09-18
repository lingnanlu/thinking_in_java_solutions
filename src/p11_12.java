
import java.util.*;

public class p11_12 {
	public static void main(String[] args){
		List<Integer> li = new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5));
		List<Integer> reverseli = new ArrayList<Integer>();
		
		ListIterator<Integer> iterator = li.listIterator(li.size());

		
		while(iterator.hasPrevious()){
			reverseli.add(iterator.previous());
		}
		
		System.out.println(reverseli);
		
	}
}
