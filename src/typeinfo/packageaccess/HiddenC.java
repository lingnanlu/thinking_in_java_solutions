package typeinfo.packageaccess;
import typeinfo.interfacea.*;
import static net.mindview.util.Print.*;
class C implements A{

	@Override
	public void f() {
		// TODO �Զ����ɵķ������
		print("public f()");
	}
	
	void u(){print("package u()");
	}
	
	protected void v(){
		print("protected v()");
	}
	
	private void w(){
		print("private w()");
	}
	
	
}
public class HiddenC {
	public static A makeA(){return new C();}
}
