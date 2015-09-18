package containers;

public abstract class Test<C> {
	String name;
	public Test(String name){
		this.name = name;
	}
	
	
	//将对不同类型测试所要求的不同容器的变化封装在了这个函数当中
	abstract void initialize(C container, TestParam tp);
	
	
	
	
	abstract int test(C container, TestParam tp);
}
