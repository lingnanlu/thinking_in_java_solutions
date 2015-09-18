
public class Leaf {
	int i = 0;
	Leaf increment(){
		i++;
		return this;
	}
	
	void print(){
		System.out.print(i);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Leaf x = new Leaf();
		x.increment().increment().increment().print();
	}

}
