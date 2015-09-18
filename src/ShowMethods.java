import java.lang.reflect.*;
import java.util.regex.Pattern;
import static net.mindview.util.Print.*;

class ShowMethods {
	private static Pattern p = Pattern.compile("(\\w+\\.|native|final)");
	public static void main(String[] args){
		int lines = 0;
		try{
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor[] ctors = c.getConstructors();
			if( args.length == 1){
				for (Method method : methods){
					print(p.matcher(method.toString()).replaceAll(""));
				}
				for (Constructor ctor : ctors){
					print(p.matcher(ctor.toString()).replaceAll(""));
				}
				lines = methods.length + ctors.length;
			} else {
				for (Method method : methods){
					if(method.toString().indexOf(args[1]) != -1){
						print(p.matcher(method.toString()).replaceAll(""));
						lines++;
					}
				}
				for (Constructor ctor : ctors){
					if(ctor.toString().indexOf(args[1]) != -1){
						print(p.matcher(ctor.toString()).replaceAll(""));
						lines++;
					}
				}
			}
		} catch(ClassNotFoundException e){
			print("No Such Class : " + e);
		}
	}
}
