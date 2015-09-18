class FailingConstructor {
	FailingConstructor(int i) throws Exception{
		if (i == 1){
			throw new Exception("Exception from FailingConstructor");
		}
		
	}
	

	

}
public class p12_22 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			FailingConstructor fc = new FailingConstructor(5);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
