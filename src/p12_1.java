
public class p12_1 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		try{
			throw new Exception("This is an exception");
		} catch(Exception e){
			System.out.println(e);
		} finally{
			System.out.println("finally");
		}
	}

}
