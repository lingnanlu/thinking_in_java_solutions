package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

class DirFilter implements FilenameFilter{

	private Pattern pattern;
	public DirFilter(String regex){
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		// TODO 自动生成的方法存根
		
		return pattern.matcher(name).find();
	}
	
}
public class DirList {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File path = new File(".");
		String[] list;
		if(args.length == 0){
			list = path.list();
		} else {
			
			list = path.list(new DirFilter(args[0]));
		}
		
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		
		for(String dirItem : list){
			System.out.println(dirItem);
		}
	}

}
