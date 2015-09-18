import java.io.IOException;
import java.nio.CharBuffer;

interface A{
	
}
class RandomWords implements Readable{

	@Override
	public int read(CharBuffer cb) throws IOException {
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
interface B extends A{
	void b();
}

interface C extends A{
	void c();
}


//一个接口可以继承多个接口
interface D extends B, C{
	
}
public class p9_13 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
