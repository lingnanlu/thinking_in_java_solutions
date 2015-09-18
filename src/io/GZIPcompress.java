package io;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
public class GZIPcompress {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		
		//这种会有乱码
		BufferedReader in = new BufferedReader(new FileReader("io/GZIPcompress.java"));
		
		
		//BufferedInputStream in = new BufferedInputStream(new FileInputStream("io/GZIPcompress.java"));
		
		
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
		
		int c;
		while((c = in.read()) != -1){
			out.write(c);
		}
		
		
		in.close();
		out.close();
		
		
		BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
		
		String s;
		while((s = in2.readLine()) != null){
			System.out.println(s);
		}
	}
	

}
