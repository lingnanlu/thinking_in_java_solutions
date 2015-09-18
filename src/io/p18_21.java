package io;

import java.io.*;
public class p18_21 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream("./io/p18_21.java"));
		
		
		//重定向只能操纵字节流
		System.setIn(in);
		
		
		
		//如果需要字符流，需要利用Adapter将字节流转化为字符流
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String s;
		while((s = br.readLine()) != null){
			System.out.print(s.toUpperCase());
		}
		
	}

}
