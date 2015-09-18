interface A{
	void foobar();
	class NestedA{
		static void barfoo(A a){
			a.foobar();
		}
	}
}
public class p10_21 implements A{
	public static void main(String[] args){
		A.NestedA test = new A.NestedA();
		test.barfoo(new p10_21());
	}

	@Override
	public void foobar() {
		// TODO 自动生成的方法存根
		System.out.println("p10_21");
	}
}
