package containers;
import net.mindview.util.*;
import java.util.Properties;
public class p17_13 {
	
	
	public static void main(String[] args){
		AssociativeArray<String, Integer> wordcounts = new 
				AssociativeArray<String, Integer>(1000);
		
		String contents = TextFile.read("../containers/test.txt");
		String[] words = contents.split("(\\s|\\W)+");
		
		for(String word : words){
			Integer count = wordcounts.get(word);
			if(count == null){
				wordcounts.put(word, 1);
			} else {
				wordcounts.put(word, ++count);
			}
		}
		
		System.out.println(wordcounts);
	}
}
