package io;
import java.io.*;

public class BasicFileOutput {
	
	static String file = "BasicFileOutput.out";
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		BufferedReader in = new BufferedReader(
				new StringReader(
						BufferedInputFile.read("./io/BasicFileOutput.java")));
		
		//PrintWriter out = new PrintWriter(
		//		new BufferedWriter(new FileWriter(file)));
		
		
		//BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
		FileWriter out = new FileWriter(file);
		int lineCount = 1;
		String s;
		while((s = in.readLine()) != null){
			out.write(lineCount++ + ": " + s + "\n");
		}
		
		out.close();
		in.close();
		
		System.out.println(BufferedInputFile.read(file));
	}

}
