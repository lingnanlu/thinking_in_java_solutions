class ImportantException extends Exception{
	public String toString(){
		return "ImportantException";
	}
}

class AnotherException extends Exception{
	public String toString(){
		return "AnotherException";
	}
}

class SecondException extends Exception{
	public String toString(){
		return "SecondException";
	}
}

public class LostMessage {

	void f() throws ImportantException {
		throw new ImportantException();
	}
	void g() throws AnotherException {
		throw new AnotherException();
	}
	
	void h() throws SecondException{
		throw new SecondException();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		LostMessage ls = new LostMessage();
		try{
			try{
				try{
					ls.f();
				} finally {
					ls.g();
				}
			} finally {
				ls.h();
			}
		} catch (Exception e){
			System.out.println(e);
		}
		
	}

}
