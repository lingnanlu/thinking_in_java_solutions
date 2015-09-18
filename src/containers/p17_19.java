package containers;
import net.mindview.util.*;
import java.util.Properties;
public class p17_19 {
	
	
	public static void main(String[] args){
		SimpleHashMap<String, Integer> wordcounts = new 
				SimpleHashMap<String, Integer>();
		
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
		
		System.out.println(wordcounts.get("1"));
	}
}
