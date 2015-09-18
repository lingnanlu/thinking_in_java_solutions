class A {
	private int a;
	private long b;
	private float c;
	private double d;
	
	A(int a, long b, float c, double d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public String toString(){
		return String.format("a = %d, b = %d, c = %f, d = %f", a, b, c, d);
	}
}
public class p13_6 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		A a = new A(1, 2, 3, 4);
		System.out.println(a);
	}

}
