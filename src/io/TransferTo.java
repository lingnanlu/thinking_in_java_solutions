package io;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class TransferTo {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		FileChannel
		in = new FileInputStream(args[0]).getChannel(),
		out = new FileOutputStream(args[1]).getChannel();
		
		in.transferTo(0, in.size(), out);
	}

}
