class MyException extends RuntimeException{
	String msg = null;
	MyException(String msg){
		this.msg = msg;
	}
	
	void show(){
		System.out.println(msg);
	}
}
public class p12_4 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
			throw new MyException("hehe");

	}

}
