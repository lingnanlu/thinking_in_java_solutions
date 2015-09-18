package io;

import java.nio.charset.Charset;
import java.util.*;
import static io.lingnanlu.github.Print.*;

public class AvailableCharSets {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SortedMap<String, Charset> charSets = 
				Charset.availableCharsets();
		
		System.out.println(System.getProperty("file.encoding"));
		Iterator<String> it = charSets.keySet().iterator();
		
		while(it.hasNext()){
			String csName = it.next();
			printnb(csName);
			Iterator<String> aliases = charSets.get(csName).aliases().iterator();
			
			if(aliases.hasNext()){
				printnb(": ");
			}
			
			while(aliases.hasNext()){
				printnb(aliases.next());
				if(aliases.hasNext())
					printnb(",");
			}
			print();
		}
	}

}
