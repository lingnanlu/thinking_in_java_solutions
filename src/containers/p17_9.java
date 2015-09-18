package containers;
import java.util.SortedSet;
import java.util.TreeSet;

import io.lingnanlu.github.*;
public class p17_9 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SortedSet<String> strset = new TreeSet<String>(new CollectionData<>(new RandomGenerator.String(), 10));
		System.out.println(strset);
	}

}
