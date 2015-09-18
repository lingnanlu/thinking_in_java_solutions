import java.util.regex.*;
import net.mindview.util.*;

public class p13_15 {
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
    // Iterate through the lines of the input file:
    int index = 0;
    Matcher m = p.matcher("");
    for(String line : new TextFile(args[0])) {
      m.reset(line);
      while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
    }
  }
} 