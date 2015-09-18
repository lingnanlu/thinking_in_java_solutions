package containers;


import io.lingnanlu.github.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;



class ListPerformance29 {
	static Random rand = new Random();
	static int reps = 1000;
	
	static List<Test<List<String>>> tests = new ArrayList<Test<List<String>>>();
	static List<Test<LinkedList<String>>> qTests = new ArrayList<Test<LinkedList<String>>>();

	static {
		tests.add(new Test<List<String>>("add"){
			@Override
			int test(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					for(int j = 0; j < listSize; j++){
						container.add("test");
					}
				}
				return loops * listSize;
			}



			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				container.clear();
			}
			
		});
		
		
		tests.add(new Test<List<String>>("get"){
			@Override
			int test(List<String> container, TestParam tp) {
				
				// TODO �Զ����ɵķ������
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.get(rand.nextInt(listSize));
				}
				return loops;
			}


			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				//���containerʹ�䲻����һ���Ͳ��Ե�Ӱ�죬��"add"�Ѿ���������������ݡ�
				//����д�и�ȱ�ݣ���Ϊ���Ե�ʱ�򣬽���������������ʱ��Ҳ��������ˡ�
				container.clear();
				container.addAll(Arrays.asList(Generated.array(String.class , new CountingGenerator.String(), tp.size)));
			}
			
		});
		
		tests.add(new Test<List<String>>("set"){
			
			
			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				container.clear();
				container.addAll(Arrays.asList(Generated.array(String.class , new CountingGenerator.String(), tp.size)));
			}

			@Override
			int test(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.set(rand.nextInt(listSize), "test");
				}
				
				return loops;
			}
			
		});
		
		
		tests.add(new Test<List<String>>("iteradd"){

			
			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				container.clear();
				container.addAll(Arrays.asList(Generated.array(String.class , new CountingGenerator.String(), tp.size)));
			}

			@Override
			int test(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				final int LOOPS = 1000000;
				
				int half = container.size() / 2;
				ListIterator<String> it = container.listIterator(half);
				for(int i = 0; i < LOOPS; i++){
					it.add("hehe");
				}
				
				return LOOPS;
			}
			
		});
		
		tests.add(new Test<List<String>>("insert"){

			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				container.clear();
				container.addAll(Arrays.asList(Generated.array(String.class , new CountingGenerator.String(), tp.size)));
			}

			@Override
			int test(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				int loops = tp.loops;
				
				for(int i = 0; i < loops; i++){
					container.add(5, "hehe");
				}
				
				return loops;
			}
			
		});
		
		tests.add(new Test<List<String>>("remove"){

			@Override
			void initialize(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			int test(List<String> container, TestParam tp) {
				// TODO �Զ����ɵķ������
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++){
					container.clear();
					container.addAll(Arrays.asList(Generated.array(String.class , new CountingGenerator.String(), tp.size)));
					while(container.size() > 5){
						container.remove(5);
					}
				}
				return loops * size;
			}
			
		});
		
	}
}


public class p17_29 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		
		
		Tester.run(params, new ArrayList<String>(), ListPerformance29.tests);
		Tester.run(params, new LinkedList<String>(), ListPerformance29.tests);
		Tester.run(params, new Vector<String>(), ListPerformance29.tests);
	}

}
