import net.mindview.util.*;
import generics.*;
public class p15_14 {
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Generator<CountedObject> gen = new BasicGenerator<CountedObject>(CountedObject.class);
		for(int i = 0; i < 10; i++){
			System.out.println(gen.next());
		}
	}

}
