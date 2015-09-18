import java.util.regex.*;
import static io.lingnanlu.github.Print.*;

public class p13_10 {
  public static void main(String[] args) {
	  String testString = "Java now has regular expressions";
	  String[] regexs = {"^Java", "\\Breg.*", "n.w\\s+h(a|i)s", "s?", "s*", "s+", 
			  "s{4}", "s{1}", "s{0,3}"};
	  
	  for (String regex : regexs){
		  print("Regex expression " + regex);
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(testString);
		  while(m.find()){
			  print("Match " + m.group() + " at position " + m.start() + "-" + (m.end() - 1));
		  }
	  }
  }
}

