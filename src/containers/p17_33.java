package containers;
import io.lingnanlu.github.CountingIntegerList;
import io.lingnanlu.github.RandomGenerator;

import java.util.*;


/*
 * 如何保持两个list数据一致呢？
 * 可以这样想，
 * 当对FastTraversalLinkdeList进行插入和删除时，即进行写操作时，为LinkedList
 * 当对FastTraversalLinkdeList进行get操作时，即进行读操作时，为ArrayList
 * 所以LinkedList中保存的永远是最新的数据。
 */
class FastTraversalLinkdeList<E>{
	private LinkedList<E> llist = new LinkedList<E>();
	private ArrayList<E> alist = new ArrayList<E>();
	private boolean isChanged = false;

	private void toArrayList(){
		alist.clear();
		alist.addAll(llist);
		isChanged = false;
	}
	
	
	public void add(E e){
		llist.add(e);
		isChanged = true;
	}
	
	public void add(int index, E e){
		llist.add(index, e);
		isChanged = true;
	}
	public boolean addAll(Collection<? extends E> c){
		isChanged = true;
		return llist.addAll(c);
	}
	
	public void clear(){
		llist.clear();
		isChanged = true;
	}
	
	public E remove(int index){
		isChanged = true;
		return llist.remove(index);
	}
	
	public E get(int index){
		if(isChanged){
			toArrayList();
		}
		return alist.get(index);
	}
	
	public int size(){
		return llist.size();
	}
	
	public String toString(){
		return llist.toString();
	}
	
	static Random rand = new Random();
	static int reps = 1000;
	static List<Test<FastTraversalLinkdeList<Integer>>> tests = new ArrayList<Test<FastTraversalLinkdeList<Integer>>>();
	
	static{
		tests.add(new Test<FastTraversalLinkdeList<Integer>>("add"){

			@Override
			void initialize(FastTraversalLinkdeList<Integer> container,
					TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
			}

			@Override
			int test(FastTraversalLinkdeList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					for(int j = 0; j < listSize; j++){
						container.add(j);
					}
				}
				return loops * listSize;
			}
			
		});
		
		tests.add(new Test<FastTraversalLinkdeList<Integer>>("remove"){

			@Override
			void initialize(FastTraversalLinkdeList<Integer> container,
					TestParam tp) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			int test(FastTraversalLinkdeList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					container.addAll(new CountingIntegerList(tp.size));
					while(container.size() > 5){
						container.remove(5);
					}
				}
				return loops * size;
			}
			
		});
		
		tests.add(new Test<FastTraversalLinkdeList<Integer>>("insert"){

			@Override
			void initialize(FastTraversalLinkdeList<Integer> container,
					TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}

			@Override
			int test(FastTraversalLinkdeList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				
				for(int i = 0; i < loops; i++){
					container.add(5, 47);
				}
				
				return loops;
			}
			
		});
		
		tests.add(new Test<FastTraversalLinkdeList<Integer>>("get"){

			@Override
			void initialize(FastTraversalLinkdeList<Integer> container,
					TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}

			@Override
			int test(FastTraversalLinkdeList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.get(rand.nextInt(listSize));
				}
				return loops;
			}
			
		});
	}
	
}
public class p17_33 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		FastTraversalLinkdeList<Integer> flist = new FastTraversalLinkdeList<Integer>();
		RandomGenerator.Integer randIntGen = new RandomGenerator.Integer();
		for(int i = 0; i < 10; i++){
			flist.add(randIntGen.next());
		}
		
		
		System.out.println(flist);
		
		for(int i = 0; i < 5; i++){
			flist.remove(3);
		}
		
		System.out.println(flist);
		
		for(int i = 0; i < 10; i++){
			flist.add(randIntGen.next());
		}
		
		
		System.out.println(flist);
		
		for(int i = 0; i < 5; i++){
			flist.remove(3);
		}
		
		System.out.println(flist);
		
		for(int i = 0; i < flist.size(); i++){
			System.out.println(flist.get(i) + " ");
		}
		
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		Tester.run(params, new ArrayList<Integer>(), ListPerformance.tests);
		Tester.run(params, new LinkedList<Integer>(), ListPerformance.tests);
		Tester.run(params, new FastTraversalLinkdeList<Integer>(), FastTraversalLinkdeList.tests);
		
		
	}

}
