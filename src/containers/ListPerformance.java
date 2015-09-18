package containers;

import io.lingnanlu.github.CountingIntegerList;
import io.lingnanlu.github.RandomGenerator;
import io.lingnanlu.github.*;
import java.util.*;


public class ListPerformance {
	static Random rand = new Random();
	static int reps = 1000;
	
	public static List<Test<List<Integer>>> tests = new ArrayList<Test<List<Integer>>>();
	public static List<Test<LinkedList<Integer>>> qTests = new ArrayList<Test<LinkedList<Integer>>>();

	static {
		tests.add(new Test<List<Integer>>("add"){
			@Override
			int test(List<Integer> container, TestParam tp) {
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



			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
			}
			
		});
		
		
		tests.add(new Test<List<Integer>>("get"){
			@Override
			int test(List<Integer> container, TestParam tp) {
				
				// TODO 自动生成的方法存根
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.get(rand.nextInt(listSize));
				}
				return loops;
			}


			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				//清空container使其不受上一类型测试的影响，如"add"已经往里面加入了内容。
				//这样写有个缺陷，因为测试的时候，将这两个方法所耗时间也计算进来了。
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}
			
		});
		
		tests.add(new Test<List<Integer>>("set"){
			
			
			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}

			@Override
			int test(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.set(rand.nextInt(listSize), 47);
				}
				
				return loops;
			}
			
		});
		
		
		tests.add(new Test<List<Integer>>("iteradd"){

			
			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}

			@Override
			int test(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				final int LOOPS = 1000000;
				
				int half = container.size() / 2;
				ListIterator<Integer> it = container.listIterator(half);
				for(int i = 0; i < LOOPS; i++){
					it.add(47);
				}
				
				return LOOPS;
			}
			
		});
		
		tests.add(new Test<List<Integer>>("insert"){

			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(new CountingIntegerList(tp.size));
			}

			@Override
			int test(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				
				for(int i = 0; i < loops; i++){
					container.add(5, 47);
				}
				
				return loops;
			}
			
		});
		
		tests.add(new Test<List<Integer>>("remove"){

			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			int test(List<Integer> container, TestParam tp) {
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
		
		tests.add(new Test<List<Integer>>("sort"){

			@Override
			void initialize(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			int test(List<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					container.addAll(Arrays.asList(Generated.array(Integer.class, new CountingGenerator.Integer(), size)));
					Collections.sort(container);
				}
				return loops;
			}
			
		});
		
		   // Tests for queue behavior:
	    qTests.add(new Test<LinkedList<Integer>>("addFirst") {
	      int test(LinkedList<Integer> list, TestParam tp) {
	        int loops = tp.loops;
	        int size = tp.size;
	        for(int i = 0; i < loops; i++) {
	          list.clear();
	          for(int j = 0; j < size; j++)
	            list.addFirst(47);
	        }
	        return loops * size;
	      }

			@Override
			void initialize(LinkedList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}
		
	    });
	    
	    qTests.add(new Test<LinkedList<Integer>>("addLast") {
	      int test(LinkedList<Integer> list, TestParam tp) {
	        int loops = tp.loops;
	        int size = tp.size;
	        for(int i = 0; i < loops; i++) {
	          list.clear();
	          for(int j = 0; j < size; j++)
	            list.addLast(47);
	        }
	        return loops * size;
	      }

			@Override
			void initialize(LinkedList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}
	    });
	    
	    qTests.add(
	      new Test<LinkedList<Integer>>("rmFirst") {
	        int test(LinkedList<Integer> list, TestParam tp) {
	          int loops = tp.loops;
	          int size = tp.size;
	          for(int i = 0; i < loops; i++) {
	            list.clear();
	            list.addAll(new CountingIntegerList(size));
	            while(list.size() > 0)
	              list.removeFirst();
	          }
	          return loops * size;
	        }

			@Override
			void initialize(LinkedList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}
	      });
	    
	    qTests.add(new Test<LinkedList<Integer>>("rmLast") {
	      int test(LinkedList<Integer> list, TestParam tp) {
	        int loops = tp.loops;
	        int size = tp.size;
	        for(int i = 0; i < loops; i++) {
	          list.clear();
	          list.addAll(new CountingIntegerList(size));
	          while(list.size() > 0)
	            list.removeLast();
	        }
	        return loops * size;
	      }

			@Override
			void initialize(LinkedList<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}
	    });
		
	}
	
	
	public static void main(String[] args){
		
		
		
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		Tester.run(params, new ArrayList<Integer>(), tests);
		Tester.run(params, new LinkedList<Integer>(), tests);
		Tester.run(params, new Vector<Integer>(), tests);
		
		Tester<LinkedList<Integer>> qTest = new Tester<LinkedList<Integer>>(params, new LinkedList<Integer>(), qTests);
		qTest.setHeadline("Queue tests");
		qTest.fieldWidth = 10;
		qTest.timedTest();
		
		
	}
}
