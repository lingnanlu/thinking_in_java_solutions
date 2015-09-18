package io;

import io.lingnanlu.github.Directory;
import io.lingnanlu.github.PPrint;
import static io.lingnanlu.github.Print.*;
import java.io.*;
public class DirectoryDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		PPrint.pprint(Directory.walk(".").dirs);
		
		for(File file : Directory.local(".", "T.*")){
			print(file);
		}
		print("-----------------");
		for(File file : Directory.walk(".", "T.*\\.java")){
			print(file);
		}
		
		
		print("=================");
		for(File file : Directory.walk(".", ".*[zZ].*\\.class")){
			print(file);
		}
	}

}
