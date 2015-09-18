package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
import net.mindview.util.*;
public class p18_1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File path = new File(".");
		String[] list;
		if(args.length == 0){
			list = path.list();
		} else {
			
			list = path.list(new FilenameFilter(){

				private Pattern pattern = Pattern.compile(args[0]);
				private Pattern a = Pattern.compile("java");
				@Override
				public boolean accept(File dir, String name) {
					// TODO 自动生成的方法存根
					if(a.matcher(name).find()){
						String fileContent = TextFile.read(name);
						return pattern.matcher(fileContent).find();
					}
					return false;
				}
				
			});
		}
		
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		
		for(String dirItem : list){
			System.out.println(dirItem);
		}
	}

}
