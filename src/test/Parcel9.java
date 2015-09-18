package test;

class A{
	public A(){
		
	}
	
	public String bar(){
		return null;
	}
	
}

public class Parcel9 {

	public A foobar(String dest){
		return new A(){
			public String bar(){
				return dest;
			}
		};
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Parcel9 test = new Parcel9();
		A a = test.foobar("hehe");
		
		System.out.println(a.bar());
	}

}
