package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


class SortedDirList{
	private String[] filenames;
	public SortedDirList(File file){
		filenames = file.list();
		Arrays.sort(filenames);
	}
	
	public String[] list(){
		return filenames;
	}
	
	public String[] list(String regex){
		Pattern pattern = Pattern.compile(regex);
		List<String> filterfilenames = new ArrayList<String>();
		for(String filename : this.filenames){
			if(pattern.matcher(filename).find()){
				filterfilenames.add(filename);
			}
		}
		
		return filterfilenames.toArray(new String[0]);
	}
}
public class p18_2 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SortedDirList dirList = new SortedDirList(new File("."));
		System.out.println(dirList);
		for(String s : dirList.list()) 
			System.out.println(s);
		System.out.println();
		for(String s : dirList.list(".+\\.java"))
			System.out.println(s);
	}

}
