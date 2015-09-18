class A{
	A(int i){
		System.out.print("Constructor " + i);
	}
}

class B{
	A foobar(){
		int y = 10;
		return new A(y){
			static int i = 5;
		};
	}
}
public class p10_15 {
	public static void main(String[] args){
		A a = new B().foobar();
	}
}
