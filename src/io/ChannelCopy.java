package io;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ChannelCopy {

	private static final int BSIZE = 1024;
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		FileChannel
			in = new FileInputStream(args[0]).getChannel(),
			out = new FileOutputStream(args[1]).getChannel();
		
		
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		
		while((in.read(buffer) != -1)){
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
	}

	
}
