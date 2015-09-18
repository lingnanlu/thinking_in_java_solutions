package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class BlockedMutex{
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		System.out.println(Thread.currentThread());
		lock.lock();
	}
	
	public void f() {
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
	
}


public class Interrupting2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}

}
