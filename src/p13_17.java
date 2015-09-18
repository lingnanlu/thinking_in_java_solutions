// : this is a tist



import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.mindview.util.*;

public class p13_17 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//String test = "// : this is a tist";
		//System.out.println(test);
		//System.out.println(test.matches("^\\s*/{2}.*"));
		Pattern p = Pattern.compile("^\\s*/{2}.*");
		
		Matcher m = p.matcher("");
		// hehe
		StringBuilder sb = new StringBuilder();
		// nothing
		for(String line : new TextFile(args[0])){
			//System.out.print(line);
			m.reset(line);
			if(m.matches())
				sb.append(line + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
