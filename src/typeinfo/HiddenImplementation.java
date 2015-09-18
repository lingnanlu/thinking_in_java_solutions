package typeinfo;
import java.lang.reflect.Method;

import typeinfo.interfacea.*;
import typeinfo.packageaccess.*;


public class HiddenImplementation {

	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		A a = HiddenC.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "w");

		
	}

	
	static void callHiddenMethod(Object a, String methodname) throws Exception{
		Method g = a.getClass().getDeclaredMethod(methodname);
		g.setAccessible(true);
		g.invoke(a);
	}
}
