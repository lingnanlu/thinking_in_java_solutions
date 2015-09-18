package io;

import io.lingnanlu.github.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class p18_19 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		Map<Byte, Integer> byteCount = new HashMap<Byte, Integer>();
		
		byte[] text = BinaryFile.read("./io/p18_17.class");
		
/*		BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(text));
		
		try {
			int c;
			Integer count = 0;
			while((c = in.read()) != -1){
				if((count = byteCount.get((byte)c)) == null){
					byteCount.put((byte)c , 1);
				} else{
					byteCount.put((byte)c, count + 1);
				}
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		
		Integer count = 0;
		for(byte b : text){
			if((count = byteCount.get(b)) == null){
				byteCount.put(b , 1);
			} else{
				byteCount.put(b, count + 1);
			}
		}
		System.out.println(byteCount);
	}
}

