package io;

import io.lingnanlu.github.Directory;
import java.io.*;
public class p18_4 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		int sum = 0;
		for(File file : Directory.walk(".", ".*\\.java")){
			System.out.println(file.getName() + "\t" + file.length());
			sum += file.length();
		}
	}

}
