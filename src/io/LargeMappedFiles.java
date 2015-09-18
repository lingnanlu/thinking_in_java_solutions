package io;


import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;

import static io.lingnanlu.github.Print.*;


public class LargeMappedFiles {
	static int length = 0x8FFFFFF;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO 自动生成的方法存根
		MappedByteBuffer out = 
				new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		
		for(int i = 0; i < length ; i++){
			out.put((byte)'x');
		}
		
		print("Finished writing");
		for(int i = length / 2; i < length / 2 + 6 ; i++){
			printnb((char)out.get(i));
		}
	}

}
