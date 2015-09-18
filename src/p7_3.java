abstract class A{
	abstract void print();
	A(){
		print();
	}
}

class B extends A{

	private int i = 5;
	@Override
	void print() {
		// TODO 自动生成的方法存根
		System.out.println("i = " + i);
	}
	
}
public class p7_3 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		B b = new B();
		b.print();
	}

}
