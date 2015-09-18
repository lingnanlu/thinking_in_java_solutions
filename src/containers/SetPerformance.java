package containers;
import io.lingnanlu.github.*;

import java.util.*;


public class SetPerformance {

	static List<Test<Set<Integer>>> tests = new ArrayList<Test<Set<Integer>>>();
	
	static {
		tests.add(new Test<Set<Integer>>("add"){

			@Override
			void initialize(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
			}

			@Override
			int test(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					for(int j = 0; j < size; j++){
						container.add(j);
					}
				}
				
				return loops * size;
			}
			
		});
		
		tests.add(new Test<Set<Integer>>("contains"){

			@Override
			void initialize(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(Arrays.asList(Generated.array(Integer.class, new CountingGenerator.Integer(), tp.size)));
			}

			@Override
			int test(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops;
				int span = tp.size * 2;
				for(int i = 0; i < loops; i++){
					for(int j = 0; j < span; j++){
						container.contains(j);
					}
				}
				
				return loops * span;
			}
			
		});
		
		tests.add(new Test<Set<Integer>>("iterate"){

			@Override
			void initialize(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				container.clear();
				container.addAll(Arrays.asList(Generated.array(Integer.class, new CountingGenerator.Integer(), tp.size)));
			}

			@Override
			int test(Set<Integer> container, TestParam tp) {
				// TODO 自动生成的方法存根
				int loops = tp.loops * 10;
				for(int i = 0; i < loops; i++){
					Iterator<Integer> it = container.iterator();
					while(it.hasNext()){
						it.next();
					}
				}
				
				return loops * container.size();
			}
			
		});
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		Tester.run(params, new TreeSet<Integer>(), tests);
		Tester.run(params, new HashSet<Integer>(), tests);
		Tester.run(params, new LinkedHashSet<Integer>(), tests);
	}

}
