package io;
import java.io.*;
public class FormattedMemoryInput {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(
				BufferedInputFile.read("./io/FormattedMemoryInput.java").getBytes()));
		
		while(in.available() != 0){
			System.out.print((char)(in.readByte()));
		}
	}

}
