package containers;

public abstract class Test<C> {
	String name;
	public Test(String name){
		this.name = name;
	}
	
	
	//���Բ�ͬ���Ͳ�����Ҫ��Ĳ�ͬ�����ı仯��װ���������������
	abstract void initialize(C container, TestParam tp);
	
	
	
	
	abstract int test(C container, TestParam tp);
}
