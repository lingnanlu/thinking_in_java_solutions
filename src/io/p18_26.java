package io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.*;

import io.lingnanlu.github.*;



abstract class JGrepTester{
	protected static int BSIZE = 500000000;


	private String name;

	public JGrepTester(String name) {
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
	
}
public class p18_26 {

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Usage: java JGrep file regex");
			System.exit(0);
		}
		Pattern p = Pattern.compile(args[1]);
		// Iterate through the lines of the input file:
		int index = 0;
		Matcher m = p.matcher("");
		
		
		//利用内存映射文件
		FileChannel fc = new RandomAccessFile(new File(args[0]), "R").getChannel();
		MappedByteBuffer bf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		String[] sa = Charset.forName(System.getProperty("file.encoding")).decode(bf).toString().split("\n");
		for (String line : sa) {
			m.reset(line);
			while (m.find())
				System.out.println(index++ + ": " + m.group() + ": "
						+ m.start());
		}
	}
}
