package containers;

import java.util.ArrayList;

public class p17_6 extends ArrayList<String>{

	@Override
	public String remove(int index) {
		// TODO 自动生成的方法存根
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		p17_6 test = new p17_6();
		test.add("A");
		test.add("B");
		System.out.println(test);
		
		test.remove(0);
	}

}
