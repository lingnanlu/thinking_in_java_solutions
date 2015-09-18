package io;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;


public class MappedIO {

	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;
	
	private static abstract class Tester{
		private String name;
		public Tester(String name){
			this.name = name;
		}
		
		public abstract void test() throws IOException;
		
		public abstract void prepare();
		public void runTest() throws IOException{
			System.out.print(name + ": ");
			prepare();
			long start = System.nanoTime();
			test();
			double duration = System.nanoTime() - start;
			System.out.format("%.2f\n", duration / 1.0e9);
		}
	}
	
	
	private static Tester[] tests = {
		new Tester("Stream Write"){

			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				DataOutputStream out = new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("temp.tmp")));
				
				for(int i = 0; i < numOfInts; i++){
					out.writeInt(i);
				}
				
				out.close();
			}

			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
				File f = new File("temp.tmp");
				if(f.exists()){
					f.delete();
				}
			}
			
		},
		
		new Tester("Mapped Write"){

			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts * 4).asIntBuffer();
				for(int i = 0; i < numOfInts; i++){
					//System.out.println(i);
					ib.put(i);
				}
				
				fc.close();
			}

			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
				File f = new File("temp.tmp");
				if(f.exists()){
					//System.out.println("Mapped Write");
					f.delete();
				}
			}
			
		},
		
		new Tester("Stream Read"){

			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				DataInputStream in = new DataInputStream(
						new BufferedInputStream(
								new FileInputStream("temp.tmp")));
				
				for(int i = 0; i < numOfInts; i++){
					in.readInt();
				}
				
				in.close();
			}

			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
			}
			
		},
		
		new Tester("Mapped Read"){

			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
				while(ib.hasRemaining()){
					ib.get();
				}
				
				fc.close();
			}

			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
				
			}
			
		},
		
		new Tester("Stream Read/Write"){

			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"),
						"rw");
				raf.writeInt(1);
				for (int i = 0; i < numOfUbuffInts; i++) {
					raf.seek(raf.length() - 4);
					raf.writeInt(raf.readInt());
				}
				raf.close();
			}

			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
			
			}
			
		},
	
		new Tester("Mapped Read/Write") {
	
			@Override
			public void test() throws IOException {
				// TODO 自动生成的方法存根
				FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw")
						.getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
						.asIntBuffer();
				ib.put(0);
				for (int i = 1; i < numOfUbuffInts; i++)
					ib.put(ib.get(i - 1));
				fc.close();
			}
	
			@Override
			public void prepare() {
				// TODO 自动生成的方法存根
	
			}
	
		},
	};
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		for(Tester tester : tests){
			tester.runTest();
		}
	}

}
