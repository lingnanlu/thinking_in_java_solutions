import java.util.*;

import static io.lingnanlu.github.Print.*;


public class p11_25 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<String> words = new TextFile("UniqueWords.java", "\\W+");
		
		Map<String, List<Integer>> wordlocation = new HashMap<String, List<Integer>>();
		
		Integer location = 0;
		
		List<Integer> list = null;
		for(String word : words){
			list = null;
			location++;
			list = wordlocation.get(word);
			if (list == null){
				list = new ArrayList<Integer>();
				list.add(location);
				wordlocation.put(word, list);
			} else{
				list.add(location);
			}
		}
		
		print(wordlocation);
	}

}
