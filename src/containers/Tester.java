package containers;

import java.util.*;

public class Tester<C> {
	
	private TestParam[] params;
	private C container;
	private List<Test<C>> tests;
	private String headline = "";
	
	
	public Tester(TestParam[] params, C container, List<Test<C>> tests){
		this.params = params;
		this.container = container;
		this.tests = tests;
		headline = container.getClass().getSimpleName();
	}
	
	public void setHeadline(String newHeadline){
		headline = newHeadline;
	}
	
	
	public static int fieldWidth = 8;
	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private static String stringField(){
		return "%" + fieldWidth + "s";
	};
	private static String numberField(){
		return "%" + fieldWidth + "d";
	}
	private void displayHeader(){
	    int width = fieldWidth * tests.size() + sizeWidth;
	    int dashLength = width - headline.length() - 1;
	    StringBuilder head = new StringBuilder(width);
	    for(int i = 0; i < dashLength/2; i++)
	      head.append('-');
	    head.append(' ');
	    head.append(headline);
	    head.append(' ');
	    for(int i = 0; i < dashLength/2; i++)
	      head.append('-');
	    System.out.println(head);

	    System.out.format(sizeField, "size");
	    for(Test test : tests)
	      System.out.format(stringField(), test.name);
	    System.out.println();
	}
	
	
	public void timedTest(){
		displayHeader();
		/*
		 * ��ͬһ��������ͬ���Ͳ��Ե�ʱ��Ҫ��ͬ����֮��Ĳ���֮���໥����Ӱ��
		 * ��ͬ����Ĳ��Զ�������Ҫ��ͬ����"add"Ҫ������Ϊ�գ�"get"Ҫ���������Ѿ���������
		 * �����������Ҫ��ͬ�Ǳ仯�Ĳ��֣���һ���ֱ���װ����Test��ľ���ʵ�ֵ��С�
		 */
		for(TestParam param : params){
			System.out.format(sizeField, param.size);
			for(Test<C> test : tests){
				test.initialize(container, param);
				long start = System.nanoTime();
				int reps = test.test(container, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps;
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}
	
	//convenient method
	
	public static <C> void run(TestParam[] params, C container, List<Test<C>> tests){
		new Tester<C>(params, container, tests).timedTest();
	}
	
}
