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
		//�������ʧ�ܣ���ôӦ�ðѵ�һ������dispose
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
		// TODO �Զ����ɵķ������
		//���ʧ���ˣ�a1����ȷdiposed����a2��û�����������Բ���Ҫdispose
		//����ɹ��ˣ���Ҫʹ��finally��ȷ��a1, a2����dispose
		try {
			FailingConstructor fc = new FailingConstructor(1);
			try{
				
			}finally{
				fc.dispose();
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}

