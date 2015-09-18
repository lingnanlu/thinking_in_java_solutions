import java.util.*;

import static io.lingnanlu.github.Print.*;


class Word{
	String name = null;
	int count = 0;
	Word(String name){
		this.name = name;
	}
	
	void count(){
		count++;
	}
	
	
	public String toString(){
		return name;
	}
}

public class p11_22 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		List<String> words = new TextFile("UniqueWords.java", "\\W+");
		Set<Word> wordSet = new HashSet<Word>();
		
		for(String word : words){
			
		}
	}

}
