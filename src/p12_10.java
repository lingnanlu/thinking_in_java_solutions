class A{
	void f() throws MyExceptionTwo{
		try{
			g();
		} catch(MyExceptionOne e){
			throw new RuntimeException(e);
		}
	}
	
	void g() throws MyExceptionOne {
		throw new MyExceptionOne();
	}
}
public class p12_10 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		try {
			new A().f();
		} catch (MyExceptionTwo e){
			e.printStackTrace(System.out);
		}
	}

}
