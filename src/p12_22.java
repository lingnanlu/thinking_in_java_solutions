class FailingConstructor {
	FailingConstructor(int i) throws Exception{
		if (i == 1){
			throw new Exception("Exception from FailingConstructor");
		}
		
	}
	

	

}
public class p12_22 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		try {
			FailingConstructor fc = new FailingConstructor(5);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
