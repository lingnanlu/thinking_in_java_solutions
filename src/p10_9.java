interface A{
	void foobar();
}

class B{

	public A concreteA(){
		return new A(){

			@Override
			public void foobar() {
				// TODO �Զ����ɵķ������
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
