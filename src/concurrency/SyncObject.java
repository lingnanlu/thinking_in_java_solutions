package concurrency;

import static io.lingnanlu.github.Print.*;

class DualSynch{
	
	private Object syncObject = new Object();
	public synchronized void f(){
		for(int i = 0; i < 5; i++){
			print("f()");
			Thread.yield();
		}
	}
	
	public void g(){
		synchronized(syncObject){
			for(int i = 0; i < 5; i++){
				print("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		final DualSynch ds = new DualSynch();
		new Thread(){
			public void run(){
				ds.f();
			}
		}.start();
		
		ds.g();
	}

}
