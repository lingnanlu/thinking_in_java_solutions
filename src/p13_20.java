import java.util.Scanner;

class A{
	private int a;
	private long b;
	private float c;
	private double d;
	private String e;
	
	A(String str){
		Scanner s = new Scanner(str);
		a = s.nextInt();
		b = s.nextLong();
		c = s.nextFloat();
		d = s.nextDouble();
		s.nextLine();
		e = s.nextLine();
	}
	
	public String toString(){
		return String.format("a = %d, b = %d, c = %f, d = %f, e = %s", a, b, c, d, e);
	}
}
public class p13_20 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		A a = new A("1 2 3.0 4.0\n this is a test");
		System.out.println(a);
	}

}
