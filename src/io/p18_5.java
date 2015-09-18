package io;

import io.lingnanlu.github.Directory;
import io.lingnanlu.github.ProcessFiles;
import io.lingnanlu.github.ProcessFiles.Strategy;

import java.io.File;
import java.io.IOException;

class ProcessFiles2 {
	
	public interface Strategy{
		void process(File file);
	}
	
	private Strategy strategy;
	private String regex;
	public ProcessFiles2(Strategy strategy, String regex){
		this.strategy = strategy;
		this.regex = regex;
	}
	
	
	public void start(String[] args) throws IOException{
		if(args.length == 0){
			processDirectoryTree(new File("."));
		} else{
			for(String arg : args){
				File file = new File(arg);
				processDirectoryTree(file);
			}
		}
	}
	
	
	public void processDirectoryTree(File root) throws IOException{
		for(File file : Directory.walk(root, regex)){
			strategy.process(file.getCanonicalFile());
		}
	}
	
	
}
public class p18_5 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		new ProcessFiles2(new ProcessFiles2.Strategy() {
				@Override
				public void process(File file) {
					// TODO 自动生成的方法存根
					System.out.println(file);
				}
			}, ".*\\.java").start(args);
	}

}
