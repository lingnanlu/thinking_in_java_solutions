abstract class A{
	
}

class B extends A{
	public void foobar(){
		System.out.println("B");
	}
	
	public static void test(A a){
		(a).foobar();
	}
}
public class p9_4 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		B.test(new B());
	}

}
