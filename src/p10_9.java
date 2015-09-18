interface A{
	void foobar();
}

class B{

	public A concreteA(){
		return new A(){

			@Override
			public void foobar() {
				// TODO 自动生成的方法存根
				System.out.println("concreteA");
			}
			
		};
	}
}

public class p10_9 {
	
	public static void main(String[] args){
		A a  = new B().concreteA();
	
	}
}
