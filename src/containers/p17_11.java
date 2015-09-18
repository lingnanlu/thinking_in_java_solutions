package containers;

import java.util.PriorityQueue;
import java.util.Random;

class IntegerWrapper implements Comparable<IntegerWrapper>{
	private static Random rand = new Random(47);
	private Integer value = rand.nextInt(100);
	@Override
	public int compareTo(IntegerWrapper o) {
		// TODO 自动生成的方法存根
		return value > o.value ? 1 : (value == o.value ? 0 : -1);
	}
	
	public String toString(){
		return value.toString();
	}
}


public class p17_11 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		PriorityQueue<IntegerWrapper> pq = new PriorityQueue<IntegerWrapper>();
		for(int i = 0; i < 10; i++){
			pq.add(new IntegerWrapper());
		}
		
		
		while(!pq.isEmpty()){
			System.out.print(pq.poll() + " ");
		}
	}

}
