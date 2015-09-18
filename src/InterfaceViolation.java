import typeinfo.interfacea.*;

class C implements typeinfo.interfacea.A{

	@Override
	public void f() {
		// TODO 自动生成的方法存根
		
	}
	
	public void g(){
		
	}
	
}
public class InterfaceViolation {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		typeinfo.interfacea.A a = new C();
		a.f();
		System.out.println(a.getClass().getName());
		if ( a instanceof C){
			C c = (C)a;
			c.g();
		}
	}

}
