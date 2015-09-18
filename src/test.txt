package io.lingnanlu.github;
import java.io.*;
import java.util.*;


public class TextFile extends ArrayList<String>{

	public static String read(String fileName){
		StringBuilder sb = new StringBuilder();
		
		try {
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
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void write(String fileName, String text){
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			out.print(text);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
	}
	
	public TextFile(String fileName, String splitter){
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0) == "") 
			remove(0);
	}
	
	public TextFile(String fileName){
		this(fileName, "\n");
	}
	
	public void write(String fileName){
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			
			for(String item : this){
				out.println(item);
			}
			
			out.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String file = read("./io/lingnanlu/github/TextFile.java");
		write("test.txt", file);
		
		TextFile text = new TextFile("test.txt");
		text.write("test2.txt");
		
		
		TreeSet<String> words = new TreeSet<String>(
				new TextFile("./io/lingnanlu/github/TextFile.java", "\\W+"));
		
		System.out.println(words.headSet("a"));
	}

}
