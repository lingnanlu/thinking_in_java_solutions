
public class p15_9 {
	public <A, B, C> void f(A a, B b, C c){
		System.out.println(a.getClass().getSimpleName());
		System.out.println(b.getClass().getSimpleName());
		System.out.println(c.getClass().getSimpleName());
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p15_9 test = new p15_9();
		test.f("hehe", 5, test);
	}

}
