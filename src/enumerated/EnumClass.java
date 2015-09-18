package enumerated;
import static io.lingnanlu.github.Print.*;
enum Shrubbery{
	GROUND, CRAWLING, HANGING
}
public class EnumClass {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(Shrubbery s : Shrubbery.values()){
			print(s + " ordinal: " + s.ordinal());
			printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
			print(s == Shrubbery.CRAWLING);
			print(s.getDeclaringClass());
			print(s.name());
			print("-----------------------");
		}
		
		for(String s : "HANGLING CRAWLING GROUND".split(" ")){
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
		}
	}

}
