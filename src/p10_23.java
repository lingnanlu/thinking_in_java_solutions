import java.util.ArrayList;
import java.util.List;

interface U{
	void methodOne();
	void methodTwo();
	void methodThree();
}

class A{
	private String name;
	A(String name){
		this.name = name;
	}
	U generateU(){
		return new U(){

			@Override
			public void methodOne() {
				// TODO 自动生成的方法存根
				System.out.println("methodOne " + name);
			}

			@Override
			public void methodTwo() {
				// TODO 自动生成的方法存根
				System.out.println("methodTwo " + name);
			}

			@Override
			public void methodThree() {
				// TODO 自动生成的方法存根
				System.out.println("methodThree " + name);
			}
			
		};
	}
}

class B{

	private List<U> us = new ArrayList<U>();
	void addU(U u){
		us.add(u);
	}
	
	void clear(){
		us.clear();
	}
	
	void travese(){
		for(U u: us){
			u.methodOne();
			u.methodTwo();
			u.methodThree();
		}
	}
}
public class p10_23 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		A a1 = new A("a1");
		A a2 = new A("a2");
		A a3 = new A("a3");
		B b1 = new B();
		b1.addU(a1.generateU());
		b1.addU(a2.generateU());
		b1.addU(a3.generateU());
		b1.travese();
		b1.clear();
		
	}

}
