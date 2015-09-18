package io;
import io.lingnanlu.github.*;

import java.io.*;
import java.util.*;

public class p18_17 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		
		String text = TextFile.read("./io/p18_17.java");
		
		BufferedReader in = new BufferedReader(new StringReader(text));
		
		try {
			int c;
			Integer count = 0;
			while((c = in.read()) != -1){
				if((count = charCount.get((char)c)) == null){
					charCount.put((char)c , 1);
				} else{
					charCount.put((char)c, count + 1);
				}
			}
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		System.out.println(charCount);
	}

}
