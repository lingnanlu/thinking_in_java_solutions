import java.util.*;

class simple extends Object{
	
}
public class p11_29 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		PriorityQueue<simple> pq = new PriorityQueue<simple>();
		for(int i = 0; i < 10; ++i){
			pq.offer(new simple());
		}
		
		System.out.println(pq);
	}

}
