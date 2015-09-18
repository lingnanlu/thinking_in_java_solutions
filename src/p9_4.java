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
		// TODO 自动生成的方法存根
		B.test(new B());
	}

}
