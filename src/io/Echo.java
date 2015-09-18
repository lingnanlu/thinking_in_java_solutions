package io;


import java.io.*;


public class Echo {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in)
				);
		
		
		String s;
		while((s = stdin.readLine()) != null && s.length() != 0){
			System.out.println(s);
		}
	}

}
