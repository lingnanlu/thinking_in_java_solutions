
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.mindview.util.*;

public class p13_18 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//String test = "// : this is a tist";
		//System.out.println(test);
		//System.out.println(test.matches("^\\s*/{2}.*"));
		Pattern p = Pattern.compile("\".*\"");
		
		// hehe
		StringBuilder sb = new StringBuilder();
		// nothing
		String file = TextFile.read(args[0]);
		Matcher m = p.matcher(file);
		
		while(m.find()){
			sb.append(m.group() + "\n");
		}
		System.out.println(sb.toString());
	}

}
