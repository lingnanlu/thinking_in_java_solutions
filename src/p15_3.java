

import typeinfo.pets.*;
public class p15_3<A, B, C, D, E, F> {

	public final A first;
	public final B second;
	public final C third;
	public final D fourth;
	public final E fifth;
	public final F sixth;
	
	public p15_3(A a, B b, C c, D d, E e, F f){
		first = a;
		second = b;
		third = c;
		fourth = d;
		fifth = e;
		sixth = f;
	}
	
	public String toString(){
		return "(" + first + "," + second + "," + third +
				"," + fourth + "," + fifth + "," + sixth + ")";
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p15_3<Cat, Cymric, Dog, EgyptianMau, Hamster, Mouse> sixtuple = 
				new p15_3(new Cat(), new Cymric(), new Dog(), new EgyptianMau(),
						new Hamster(), new Mouse());
		
		System.out.println(sixtuple);
	}

}
