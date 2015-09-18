package containers;
import java.util.*;
import net.mindview.util.*;

public class p17_4 extends ArrayList<String>{
	
	
	public p17_4(String fileName){
		String[] words = TextFile.read(fileName).split("\\s");
		
		for(String word : words){
			System.out.println(word);
			add(word);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p17_4 a = new p17_4("./containers/p17_4.java");
		List<String> wordlist = new ArrayList<String>(a);
		Set<String> wordset = new HashSet<String>(a);
		System.out.println(wordlist);
		
		System.out.println(wordset);
	}

}
