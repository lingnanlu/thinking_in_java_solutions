package io;

import io.lingnanlu.github.TextFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

class TextFile2 extends ArrayList<String>{

	public static String read(String fileName) throws IOException{
		StringBuilder sb = new StringBuilder();
		
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			String s;
			try {
				while((s = in.readLine()) != null){
					sb.append(s);
					sb.append("\n");
				}
			} finally{
				in.close();
			}
		return sb.toString();
	}
	
	public static void write(String fileName, String text) throws IOException{
		
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			out.print(text);
			out.close();

	}
	
	public TextFile2(String fileName, String splitter) throws IOException{
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0) == "") 
			remove(0);
	}
	
	public TextFile2(String fileName) throws IOException{
		this(fileName, "\n");
	}
	
	public void write(String fileName) throws FileNotFoundException{
		
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			
			for(String item : this){
				out.println(item);
			}
			
			out.close();
	
	}

}
public class p18_18 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		String file = TextFile2.read("./io/lingnanlu/github/TextFile.java");
		TextFile2.write("test.txt", file);
		
		TextFile text = new TextFile("test.txt");
		text.write("test2.txt");
		
		
		TreeSet<String> words = new TreeSet<String>(
				new TextFile("./io/lingnanlu/github/TextFile.java", "\\W+"));
		
		System.out.println(words.headSet("a"));
	}

}
