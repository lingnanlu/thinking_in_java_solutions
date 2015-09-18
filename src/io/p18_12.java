package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class p18_12 {

	static String outputfile = "p18_12_output.out";
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		List<String> stringlist = new LinkedList<String>();
		String s;
		while((s = in.readLine() )!= null){
			stringlist.add(s);
		}
		
		int lineCount = 1;
		PrintWriter out = new PrintWriter(outputfile);
		ListIterator<String> it = stringlist.listIterator(stringlist.size());
		while(it.hasPrevious()){
			out.println(lineCount++ + ": " + it.previous());
		}
		
		out.close();
	}
}
