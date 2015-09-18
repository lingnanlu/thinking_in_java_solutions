import java.util.*;

public class AddingGroups {
	public static void main(String[] args){
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4,5));
		Integer[] moreInts = {7, 9, 10, 11};
		collection.addAll(Arrays.asList(moreInts));
		Collections.addAll(collection, 4,5,6,7,8);
		Collections.addAll(collection, moreInts);
		List<Integer> list = Arrays.asList(16, 17, 18, 19);
		list.set(1, 99);
		list.add(21);
	}
}
