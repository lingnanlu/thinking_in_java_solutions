import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

import enumerated.*;

class Foobar{
	public static int i = 5;
}
public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<Foobar> cls = Foobar.class;
		
		System.out.println(cls.getField("i").get(null));
		
		Foobar.i = 6;
		
		System.out.println(cls.getField("i").get(null));
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("foobar.out"));
		
		out.writeObject(cls);
		
	}

}
