import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class AnotherTest {

	public static void main(String[] args) throws IOException, Exception {
		// TODO �Զ����ɵķ������
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("foobar.out"));
		
		Class<Foobar> cls = (Class<Foobar>) in.readObject();
		
		System.out.println(cls.getField("i").get(null));
	}

}
