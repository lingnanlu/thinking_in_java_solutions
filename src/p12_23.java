class A {
	boolean statue = false;
	String name;	
	A(String name){
		this.name = name;
		statue = true;
	}
	
	void dispose(){
		statue = false;
	}
	
	public String toString(){
		return "name " + name + statue;
	}
}

class FailingConstructor {

	private A a1;
	private A a2;
	
	FailingConstructor(int i) throws Exception{
		
		a1 = new A("A1");
		System.out.println("creating " + a1);
		//如果创建失败，那么应该把第一个对象dispose
		try{
			if (i == 1){
				throw new Exception("Exception from FailingConstructor");
			}
		} catch(Exception e){
			a1.dispose();
			System.out.println("disposing " + a1);
			throw e;
		}
		
		a2 = new A("A2");
		System.out.println("creating " + a2);
	}
	
	void dispose(){
		a1.dispose();
		System.out.println("disposing " + a1);
		a2.dispose();
		System.out.println("disposing " + a2);
	}

}
public class p12_23 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//如果失败了，a1被正确diposed，而a2还没被创建，所以不需要dispose
		//如果成功了，需要使用finally来确保a1, a2都被dispose
		try {
			FailingConstructor fc = new FailingConstructor(1);
			try{
				
			}finally{
				fc.dispose();
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}

