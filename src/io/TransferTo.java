package io;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class TransferTo {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		FileChannel
		in = new FileInputStream(args[0]).getChannel(),
		out = new FileOutputStream(args[1]).getChannel();
		
		in.transferTo(0, in.size(), out);
	}

}
