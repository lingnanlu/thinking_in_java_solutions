package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.io.Writer;

public class p18_13 {

	
	public static long timedTest(Writer writer) throws IOException{
		
		long start = System.nanoTime();
		int outerReps = 1000;
		int innerReps = 10000; 
		while(outerReps-- != 0){
			try {
				for(int i = 0; i < innerReps; i++)
				writer.write("test\n");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		writer.close();
		long duration = System.nanoTime() - start;
		return duration;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		String filename = "temp.out";
		BufferedWriter bufferOut = new BufferedWriter(new FileWriter(filename));
		System.out.println("buffered\t" + timedTest(bufferOut));
		
		
		new File(filename).delete();
		FileWriter nobufferout = new FileWriter(filename);
		
		System.out.println("nobuffered\t" + timedTest(nobufferout));
	}


}
