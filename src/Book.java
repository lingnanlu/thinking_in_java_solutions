
public class Book {
	boolean checkedOut = false;
	Book(boolean checkOut){
		checkedOut = checkOut;
	}
	
	void checkIn(){
		checkedOut = false;
	}
	
	protected void finalize(){
		if(checkedOut){
			System.out.println("Error: checked out");
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Book novel = new Book(true);
		novel.checkIn();
		
		new Book(true);
		System.gc();
	}

}
