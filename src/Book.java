
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
		// TODO �Զ����ɵķ������
		Book novel = new Book(true);
		novel.checkIn();
		
		new Book(true);
		System.gc();
	}

}
