import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class p11_20 {

	public static void main(String[] args){
		Set<Character> alphaSet = new HashSet<Character>();
		Character[] alphas = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
		Collections.addAll(alphaSet, alphas);
		
		Map<String, Integer> wordcounts = new HashMap<String, Integer>();
		
		Set<String> words = new HashSet<String>(new TextFile("UniqueWords.java", "\\W+"));
		
		Map<Character, Integer> alphaCountMap = new HashMap<Character, Integer>();
		
		for(String word : words){
			int count = 0;
			for(Character v : word.toCharArray()){
				if(alphaSet.contains(v)){
					Integer charCount = alphaCountMap.get(v);
					alphaCountMap.put(v, charCount == null ? 1 : charCount + 1);
					count++;
				}
			}
			wordcounts.put(word, count);
		}
		
		
		System.out.println(alphaSet);
		System.out.println(words);
		System.out.println(wordcounts);

		System.out.println(alphaCountMap);
		
	}

}
