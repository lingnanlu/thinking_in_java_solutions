class A{
	public static class B{
		B(){
			System.out.println("B");
		}
	}
}
public class p10_18 {
	public static void main(String[] args){
		A.B b = new A.B();
	}
}
