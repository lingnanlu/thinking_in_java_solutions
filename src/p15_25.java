interface p15_25_A{
	void A();
}

interface p15_25_B{
	void B();
}

class p15_25_Impl implements p15_25_A, p15_25_B{

	@Override
	public void B() {
		// TODO �Զ����ɵķ������
		System.out.println("B");
	}

	@Override
	public void A() {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		p15_25_Impl a = new p15_25_Impl();
		genericA(a);
		genericB(a);
	}

}
