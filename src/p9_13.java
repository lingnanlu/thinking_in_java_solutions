import java.io.IOException;
import java.nio.CharBuffer;

interface A{
	
}
class RandomWords implements Readable{

	@Override
	public int read(CharBuffer cb) throws IOException {
		// TODO �Զ����ɵķ������
		return 0;
	}
	
}
interface B extends A{
	void b();
}

interface C extends A{
	void c();
}


//һ���ӿڿ��Լ̳ж���ӿ�
interface D extends B, C{
	
}
public class p9_13 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
