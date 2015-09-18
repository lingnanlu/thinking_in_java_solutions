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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		test(RandomGenerator.class);
	}

}
