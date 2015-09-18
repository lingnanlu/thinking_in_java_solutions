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
		// TODO 自动生成的方法存根
		try {
			Class cls = Class.forName(args[0]);
			print(cls, "");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Class not found");
		}
		
		
	}

}
