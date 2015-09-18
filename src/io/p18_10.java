package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

public class p18_10 {
	
	public static void usage(){
		System.out.println("usage:" + "\n"
				+ "p18_10 filename" + "\n"
				+ "p18_10 filename" + "-s searchword");
	}

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		if(args.length == 0){
			usage();
			System.exit(1);
		}
		
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		List<String> stringlist = new LinkedList<String>();
		String s;
		
		Pattern pattern = null;
		if(args.length == 3){
			pattern = Pattern.compile(args[2]);
			
		}
		while((s = in.readLine() )!= null){
			if(pattern != null){
				if(pattern.matcher(s).find()){
					stringlist.add(s);
				}
			} else {
				stringlist.add(s);
			}
		
		}
		
		ListIterator<String> it = stringlist.listIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
