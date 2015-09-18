import java.io.File;
import java.util.regex.*;

import net.mindview.util.*;

public class p13_16 {
  public static void main(String[] args) throws Exception {
    if(args.length < 2) {
      System.out.println("Usage: java p13_15 file regex flags");
      System.out.println("flags: \n -i: CASE_INSENSITIVE \n -m: MULTILINE");
      System.exit(0);
    }
    
    System.out.println(args[1]);
    Pattern p = null;
    if(args[2] == "-i"){
    	 p = Pattern.compile(args[1], Pattern.CASE_INSENSITIVE); 
    } else if (args[2] == "-m"){
    	 p = Pattern.compile(args[1], Pattern.MULTILINE); 
    } else{
    	 p = Pattern.compile(args[1]); 
    }
    
    File[] files;
    if(args[0].matches(".*/")){
    	files = new File(args[0]).listFiles();
    } else{
    	files = new File[1];
    	files[0] = new File(args[0]);
    }
    
    for(File file : files){
    	System.out.println(file);
    }
    // Iterate through the lines of the input file:
    int index = 0;
    Matcher m = p.matcher("");
    for(File file : files){
    	 for(String line : new TextFile(file.toString())) {
    	    	m.reset(line);
    	    	while(m.find())
    	    		System.out.println(index++ + ": " + m.group() + ": " + m.start());
    	 }
    }
   
  }
} 