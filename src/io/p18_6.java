package io;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.lingnanlu.github.*;

public class p18_6 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		new ProcessFiles(new ProcessFiles.Strategy() {
			
			@Override
			public void process(File file) {
				// TODO 自动生成的方法存根
				
				
				System.out.println(new Date(file.lastModified()));

				
			}
			
		}, "java").start(args);;
		
		System.out.println();
		new ProcessFiles(new ProcessFiles.Strategy() {
			
			@Override
			public void process(File file) {
				// TODO 自动生成的方法存根
				
				
				Date modDate = new Date(file.lastModified());
				try {
					if(modDate.after(sdf.parse("05/01/2015"))){
						System.out.println(modDate);
					}
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			
		}, "java").start(args);;
	}

}
