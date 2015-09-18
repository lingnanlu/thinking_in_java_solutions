package containers;
import java.util.*;
import static io.lingnanlu.github.Print.*;
class CountedString {
	  private static List<String> created =
	    new ArrayList<String>();
	  private String s;
	  private int id = 0;
	  private char alpha;
	  public CountedString(String str, char alpha) {
	    s = str;
	    this.alpha = alpha;
	    created.add(s);
	    // id is the total number of instances
	    // of this string in use by CountedString:
	    for(String s2 : created)
	      if(s2.equals(s))
	        id++;
	  }
	  public String toString() {
	    return "String: " + s + " id: " + id + " char: " + alpha + 
	      " hashCode(): " + hashCode();
	  }
	  public int hashCode() {
	    // The very simple approach:
	    // return s.hashCode() * id;
	    // Using Joshua Bloch's recipe:
	    int result = 17;
	    result = 37 * result + s.hashCode();
	    result = 37 * result + id;
	    result = 37 * result + alpha;
	    return result;
	  }
	  public boolean equals(Object o) {
	    return o instanceof CountedString &&
	      s.equals(((CountedString)o).s) &&
	      id == ((CountedString)o).id && 
	      alpha == ((CountedString)o).alpha;
	  }
}

public class p17_26 {

	 public static void main(String[] args) {
		    Map<CountedString,Integer> map =
		      new HashMap<CountedString,Integer>();
		    CountedString[] cs = new CountedString[5];
		    Random rand = new Random(47);
		    for(int i = 0; i < cs.length; i++) {
		      cs[i] = new CountedString("hi", (char)(rand.nextInt(255)));
		      map.put(cs[i], i); // Autobox int -> Integer
		    }
		    print(map);
		    for(CountedString cstring : cs) {
		      print("Looking up " + cstring);
		      print(map.get(cstring));
		    }
		  }
}


