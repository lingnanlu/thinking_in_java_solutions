package io;

import java.io.*;

public class MemoryInput {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		StringReader in = new StringReader(BufferedInputFile.read("./io/MemoryInput.java"));
		int c;
		while((c = in.read()) != -1){
			//java中的char为两个字节，可用来保存unicode字符
			System.out.print((char)c);
		}
	}

}
