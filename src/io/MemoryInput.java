package io;

import java.io.*;

public class MemoryInput {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		StringReader in = new StringReader(BufferedInputFile.read("./io/MemoryInput.java"));
		int c;
		while((c = in.read()) != -1){
			//java�е�charΪ�����ֽڣ�����������unicode�ַ�
			System.out.print((char)c);
		}
	}

}
