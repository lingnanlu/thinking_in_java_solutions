import java.util.*;
import static io.lingnanlu.github.Print.*;

public class p11_21 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<String> words = new TextFile("UniqueWords.java", "\\W+");
		
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		for(String word : words){
			Integer count = wordCount.get(word);
			wordCount.put(word, count == null ? 1 : count + 1);
		}
		
		print(wordCount);
		print();
		List<String> wordList = new ArrayList<String>(wordCount.keySet());
		
		print(wordList);
		print();
		Collections.sort(wordList, String.CASE_INSENSITIVE_ORDER);
		
		print(wordList);
		print();
		
		Map<String, Integer> sortedWordCount = new LinkedHashMap<String, Integer>();
		
		for(String word : wordList){
			sortedWordCount.put(word, wordCount.get(word));
		}
		
		print(sortedWordCount);
	}

}
