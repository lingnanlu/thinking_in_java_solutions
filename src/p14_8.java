class A{
	
}

class B extends A{
	
}

class C extends B{
	
}


public class p14_8 {
	static void print(Class cls, String hyphen){
		if (cls.getName() == "java.lang.Object"){
			System.out.println(hyphen + "object");
			return;
		} else{
			System.out.println(hyphen + cls.getName());
			print(cls.getSuperclass(), hyphen + "----");
		}
		
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		try {
			Class cls = Class.forName(args[0]);
			print(cls, "");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("Class not found");
		}
		
		
	}

}
