package io;


import io.lingnanlu.github.OSExecuteException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class OSExecute2 {
	public static List<String> command(String command){
		boolean err = false;
		List<String> result = new ArrayList<String>();
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String s;
			while((s = results.readLine()) != null){
				result.add(s);
			}
			
			
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			while((s = errors.readLine()) != null){
				System.err.println(s);
				err = true;
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		if(err){
			throw new OSExecuteException("Errors executing " + command);
		}
		
		return result;
	}
}
public class p18_22 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		List<String> result = OSExecute2.command("ls");
		
		for(String item : result){
			System.out.println(item);
		}
	}

}
