package io;

import java.io.File;
import java.lang.reflect.*;
import java.util.regex.Pattern;

import static io.lingnanlu.github.Print.*;
public class FileTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file = new File(".");
		Pattern pattern = Pattern.compile("java\\.io\\.File");
		Class<File> cls = File.class;
		for(Method method : cls.getMethods()){
			if(method.getParameters().length == 0 && 
					pattern.matcher(method.toString()).find())
				try {
					print(method.getName() + "\t\t" + method.invoke(file));
				} catch (IllegalAccessException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
		
		print("\n");
		File absolutefile = new File("D:\\a");
		for(Method method : cls.getMethods()){
			if(method.getParameters().length == 0 && 
					pattern.matcher(method.toString()).find())
				try {
					print(method.getName() + "\t\t" + method.invoke(absolutefile));
				} catch (IllegalAccessException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
	}

}
