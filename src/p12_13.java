class A{
	void foobar(){
		
	}
}
public class p12_13 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		A a = null;
		try{
			a.foobar();
		} finally{
			System.out.println("hehe");
		}
	}

}
