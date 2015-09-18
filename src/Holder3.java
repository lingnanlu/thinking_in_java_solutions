import typeinfo.pets.*;


public class Holder3<T> {
	private T a;
	public Holder3(T a){
		this.a = a;
	}
	
	public void set(T a){
		this.a = a;
	}
	
	public T get(){
		return a;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Holder3<Pet> h3 = new Holder3<Pet>(new Mouse());
		Pet mouse = h3.get();
		System.out.println(mouse);
	}

}
