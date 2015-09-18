package io.lingnanlu.github;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO 自动生成的方法存根
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
