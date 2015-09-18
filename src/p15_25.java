interface p15_25_A{
	void A();
}

interface p15_25_B{
	void B();
}

class p15_25_Impl implements p15_25_A, p15_25_B{

	@Override
	public void B() {
		// TODO 自动生成的方法存根
		System.out.println("B");
	}

	@Override
	public void A() {
		// TODO 自动生成的方法存根
		System.out.println("A");
	}
	
}


public class p15_25 {

	static <T extends p15_25_A> void genericA(T para){
		para.A();
	}
	
	static <T extends p15_25_B> void genericB(T para){
		para.B();
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p15_25_Impl a = new p15_25_Impl();
		genericA(a);
		genericB(a);
	}

}
