package containers;
import java.util.*;

import io.lingnanlu.github.*;

class StringContainer{
	private int index;
	private final int increment = 10;
	private String[] strs;
	private int initsize;
	public StringContainer(int size){
		initsize = size;
		strs = new String[size];
		index = 0;
	}
	
	
	static Random rand = new Random();
	static int reps = 1000;
	
	
	public void add(String str){
		if( index < strs.length){
			strs[index++] = str;
		} else {
			String[] newstrs = new String[strs.length + increment];
			for(int i = 0; i < strs.length; i++){
				newstrs[i] = strs[i];
			}
			strs = newstrs;
			strs[index++] = str;
		}
	}
	
	public int size(){
		return index;
	}
	
	public void clear(){
		strs = new String[initsize];
		index = 0;
	}
	public String get(int index){
		return strs[index];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < index ; i++){
			sb.append(strs[i] + " ");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	static List<Test<StringContainer>> tests = new ArrayList<Test<StringContainer>>();
	
	static{
		tests.add(new Test<StringContainer>("add"){

			@Override
			void initialize(StringContainer container, TestParam tp) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			int test(StringContainer container, TestParam tp) {
				// TODO 自动生成的方法存根
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
			
		});
		
		tests.add(new Test<StringContainer>("get"){
			@Override
			int test(StringContainer container, TestParam tp) {
				
				// TODO 自动生成的方法存根
				int loops = tp.loops * reps;
				int listSize = container.size();
				for(int i = 0; i < loops; i++){
					container.get(rand.nextInt(listSize));
				}
				return loops;
			}


			@Override
			void initialize(StringContainer container, TestParam tp) {
				container.clear();
				RandomGenerator.String randStringGen = new RandomGenerator.String();
				for(int i = 0; i < tp.size; i++){
					container.add(randStringGen.next());
				}
			}
			
		});
	}
}

public class p17_31 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		StringContainer sc = new StringContainer(20);
		RandomGenerator.String randStringGen = new RandomGenerator.String();
		for(int i = 0; i < 56; i++){
			sc.add(randStringGen.next());
		}
		
		System.out.println(sc);
		System.out.println(sc.size());

		
		TestParam[] params = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		
		Tester.run(params, new ArrayList<String>(), ListPerformance29.tests.subList(1, 3));
		
		Tester.run(params, new StringContainer(10), StringContainer.tests);
		
	}

}
