package io.lingnanlu.github;

import java.io.File;
import java.io.IOException;

public class ProcessFiles {
	
	public interface Strategy{
		void process(File file);
	}
	
	private Strategy strategy;
	private String ext;
	public ProcessFiles(Strategy strategy, String ext){
		this.strategy = strategy;
		this.ext = ext;
	}
	
	
	public void start(String[] args) throws IOException{
		if(args.length == 0){
			processDirectoryTree(new File("."));
		} else{
			for(String arg : args){
				File file = new File(arg);
				if(file.isDirectory()){
					processDirectoryTree(file);
				} else{
					if(!arg.endsWith("." + ext)){
						arg += "." + ext;
						strategy.process(new File(arg).getCanonicalFile());
					}
				}
			}
		}
	}
	
	
	public void processDirectoryTree(File root) throws IOException{
		for(File file : Directory.walk(root, ".*\\." + ext)){
			strategy.process(file.getCanonicalFile());
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		new ProcessFiles(new ProcessFiles.Strategy() {
			
			@Override
			public void process(File file) {
				// TODO 自动生成的方法存根
				System.out.println(file);
			}
		}, "java").start(args);
	}
}
