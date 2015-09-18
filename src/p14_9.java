import java.lang.reflect.Field;

class A{
int A_1;
int A_2;
}

class B extends A{
int B_1;
int B_2;
}

class C extends B{
int C_1;
int C_2;
}


public class p14_9 {
	static void printFields(Class cls, String hyphen){
		if (cls.getName() == "java.lang.Object"){
			for(Field field : cls.getDeclaredFields()){
				System.out.println(hyphen + field);
			}
			return;
		} else{
			for(Field field : cls.getDeclaredFields()){
				System.out.println(hyphen + field);
			}
			printFields(cls.getSuperclass(), hyphen + "----");
		}
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			Class cls = Class.forName(args[0]);
			printFields(cls, "");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Class not found");
		}
		
		
	}

}
