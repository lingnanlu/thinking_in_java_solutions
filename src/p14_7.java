import static io.lingnanlu.github.Print.*;

class Candy {
  static { print("Loading Candy"); }
}

class Gum {
  static { print("Loading Gum"); }
}

class Cookie {
  static { print("Loading Cookie"); }
}

public class p14_7 {
  public static void main(String[] args) {	
    print("inside main");
    
    for(int i = 0; i < args.length; i++){
    	try {
    		print(i);
			Class.forName(args[i]);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			 print("Couldn't find Gum");
		}
    }
  }
} 