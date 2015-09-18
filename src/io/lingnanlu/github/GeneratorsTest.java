package io.lingnanlu.github;

public class GeneratorsTest {
	public static int size = 10;
	public static void test(Class<?> surroundingClass){
		for(Class<?> type : surroundingClass.getClasses()){
			System.out.print(type.getSimpleName() + ": ");
			try {
				Generator<?> g = (Generator<?>) type.newInstance();
				for(int i = 0; i < size; i++){
					System.out.printf(g.next() + " ");
				}
				System.out.println();
			} catch (InstantiationException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		test(RandomGenerator.class);
	}

}
