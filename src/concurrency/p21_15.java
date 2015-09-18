package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test{
	
	private Object object1 = new Object();
	private Object object2 = new Object();
	
	public  void method1(){
		synchronized (this) {
			for(int i = 0; i < 10; i++){
				System.out.println("method One");
				Thread.yield();
			}
		}
	}
	
	public  void method2(){
		synchronized (object1) {
			for(int i = 0; i < 10; i++){
				System.out.println("method Two");
				Thread.yield();
			}
		}
	}
	
	public  void method3(){
		synchronized (object2) {
			for(int i = 0; i < 10; i++){
				System.out.println("method Three");
				Thread.yield();
			}
		}
	}
	
}

class TestWithLock {

	private Lock lock1 = new ReentrantLock();
	//private Lock lock2 = new ReentrantLock();
	//private Lock lock3 = new ReentrantLock();

	public void method1() {
		lock1.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("method One");
				Thread.yield();
			}
		} finally {
			lock1.unlock();
		}

	}

	public void method2() {
		lock1.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("method Two");
				Thread.yield();
			}
		} finally {
			lock1.unlock();
		}

	}

	public void method3() {
		lock1.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("method Three");
				Thread.yield();
			}
		} finally {
			lock1.unlock();
		}

	}

}

public class p21_15 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		final TestWithLock ts = new TestWithLock();
		
		new Thread(){
			public void run(){
				ts.method1();
			}
		}.start();
		
		new Thread(){
			public void run(){
				ts.method2();
			}
		}.start();
		
		new Thread(){
			public void run(){
				ts.method3();
			}
		}.start();
	}

}
