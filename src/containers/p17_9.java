package containers;
import java.util.SortedSet;
import java.util.TreeSet;

import io.lingnanlu.github.*;
public class p17_9 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SortedSet<String> strset = new TreeSet<String>(new CollectionData<>(new RandomGenerator.String(), 10));
		System.out.println(strset);
	}

}
