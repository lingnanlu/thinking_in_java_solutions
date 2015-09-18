//: net/mindview/atunit/AtUnit.java
// An annotation-based unit-test framework.
// {RunByHand}
package io.lingnanlu.github.atunit;

import java.lang.reflect.*;
import java.io.*;
import java.util.*;

import net.mindview.atunit.ClassNameFinder;
import io.lingnanlu.github.*;
import static io.lingnanlu.github.Print.*;

public class AtUnit implements ProcessFiles.Strategy {

	private static Class<?> testClass;
	
	private static int testRun;
	private static int failedCount;
	private static List<String> failedTests = new ArrayList<String>();
	public static void main(String[] args) throws Exception{
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
		
		new ProcessFiles(new AtUnit(), "class").start(args);
		if(failedCount == 0){
			print("OK (" + testRun + " tests)");
		} else{
			print("(" + testRun + " tests)");
			print("\n>>> " + failedCount + " FAILURE" + (failedCount > 1 ? "S" : "") + " <<<");
			
			for(String failure : failedTests){
				print("\t" + failure);
			}
		}
	}
	static class TestMethods extends ArrayList<Method>{
		void addIfTestMethod(Method m){
			if(m.getAnnotation(Test.class) == null)
				return;
			if(!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class)))
				throw new RuntimeException("@Test method" + " must return boolean or void");
			
			m.setAccessible(true);
			add(m);
		}
	}
	
	private static Method checkForCreatorMethod(Method m){
		if(m.getAnnotation(TestObjectCreate.class) == null)
			return null;
		
		if(!(m.getReturnType().equals(testClass)))
			throw new RuntimeException("@TestObjectCreate must return instance of Class to be tested");
		
		if((m.getModifiers() & java.lang.reflect.Modifier.STATIC) < 1)
			throw new RuntimeException("@TestObjectCreate must be static");
		m.setAccessible(true);
		return m;
	}
	@Override
	public void process(File file) {
		// TODO �Զ����ɵķ������
			
			try {
				String cName = ClassNameFinder.thisClass(BinaryFile.read(file));
				testClass = Class.forName(cName);
				print(testClass.getName());
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			
			//�õ�Test������TestObjectCreator����
			TestMethods testMethods = new TestMethods();
			Method creator = null;
			for(Method m : testClass.getDeclaredMethods()){
				testMethods.addIfTestMethod(m);
				
				
				if (creator == null){
					creator = checkForCreatorMethod(m);
				}
			}
			
			if(testMethods.size() > 0 ){
				if(creator == null){
					try {
						if(!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())){
							print("Error: " + testClass + " default constructor must be public");
						}
					} catch (NoSuchMethodException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
			
			for(Method m : testMethods){
				
				
				TestNote anns = null;
				if( (anns = m.getAnnotation(TestNote.class))  != null){
					print(anns.note());
				}
					
				printnb("\t" + m.getName() + " ");
				testRun++;
				//Ϊÿһ��Test����������һ���µ�ʵ��
			
				Object obj = createTestObject(creator);
				
				boolean success = false;
				
				
				//���쳣�Ĵ������ͽ������ȫ�ŵ����棬��ô�������쳣ʱ�������������������ľͲ��ö�λ
				try{
					if(m.getReturnType().equals(boolean.class)){
						success = (boolean) m.invoke(obj);
					} else{
							m.invoke(obj);
							success = true;
					}
				} catch(InvocationTargetException e){
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
	
				print(success ? "" : "(failed)");
				if(success == false){
					failedTests.add(testClass.getName() + ": " + m.getName());
					failedCount++;
				}
			}
			
			
			

	}
	
	
	private static Object createTestObject(Method creator){

		if (creator != null) {
			try {
				return creator.invoke(testClass);
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				throw new RuntimeException("Couldn't run @TestObject method");
			} 
		} else {
			try {
				return  testClass.newInstance();
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				throw new RuntimeException("Couldn't create a object");
			} 
		}

	}

} // /:~
