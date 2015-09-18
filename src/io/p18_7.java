package io;
import java.io.*;
import java.util.*;

public class p18_7 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		List<String> stringlist = new LinkedList<String>();
		String s;
		while((s = in.readLine() )!= null){
			stringlist.add(s);
		}
		
		ListIterator<String> it = stringlist.listIterator(stringlist.size());
		while(it.hasPrevious()){
			System.out.println(it.previous());
		}
	}

}
