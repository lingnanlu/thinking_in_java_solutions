interface HasTwoMethod{
	void first();
	void second();
}

class HasTwoMethodImpl implements HasTwoMethod{
	public void first(){
		System.out.println("first");
	}
	public void second(){
		System.out.println("second");
	}
	
	public void third(){
		System.out.println("third");
	}
}
public class p15_20 {

	public static <T extends HasTwoMethod> void foobar(T x){
		x.first();
		x.second();
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		foobar(new HasTwoMethodImpl());
	}

}
