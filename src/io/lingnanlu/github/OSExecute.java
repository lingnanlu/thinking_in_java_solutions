package io.lingnanlu.github;

import java.io.IOException;
import java.io.*;
public class OSExecute {
	public static void command(String command){
		boolean err = false;
		
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String s;
			while((s = results.readLine()) != null){
				System.out.println(s);
			}
			
			
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			while((s = errors.readLine()) != null){
				System.err.println(s);
				err = true;
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		if(err){
			throw new OSExecuteException("Errors executing " + command);
		}
	}
}
