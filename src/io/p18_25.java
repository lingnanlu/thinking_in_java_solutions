package io;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;


abstract class ByteBufferTester {

	protected static int BSIZE = 500000000;


	private String name;

	public ByteBufferTester(String name) {
		this.name = name;
	}

	public abstract void test() throws IOException;

	public abstract void prepare() throws FileNotFoundException;

	public void runTest() throws IOException {
		System.out.print(name + ": ");
		prepare();
		long start = System.nanoTime();
		test();
		double duration = System.nanoTime() - start;
		System.out.format("%.2f\n", duration / 1.0e6);
	}
	
	
	
	public static ByteBufferTester[] tests = {
		new ByteBufferTester("allocate"){
			FileChannel fc;
			ByteBuffer bf;
			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				fc.read(bf);
				fc.close();
			}

			@Override
			public void prepare() throws FileNotFoundException {
				// TODO 自动生成的方法存根
				bf = ByteBuffer.allocate(BSIZE);
				fc = new FileInputStream(new File("test.dat")).getChannel();
			}
			
		},
		
		new ByteBufferTester("directedallocate"){
			FileChannel fc;
			ByteBuffer bf;
			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				fc.read(bf);
				fc.close();
			}

			@Override
			public void prepare() throws FileNotFoundException {
				// TODO 自动生成的方法存根
				bf = ByteBuffer.allocateDirect(BSIZE);
				fc = new FileInputStream(new File("test.dat")).getChannel();
			}
			
		},
		
	};
	
}

public class p18_25 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		for(ByteBufferTester test : ByteBufferTester.tests)
			test.runTest();
	}

}


