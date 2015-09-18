package io;
import java.io.*;


public class Redirecting {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		PrintStream console = System.out;
		
		
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream("./io/Redirecting.java"));
		
		System.setIn(in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		PrintStream out = new PrintStream(
				new BufferedOutputStream(
						new FileOutputStream("test.out")));
		
		
		System.setOut(out);
		System.setErr(out);
		
		
		String s;
		while((s = br.readLine()) != null){
			System.out.println(s);
		}
		
		out.close();
		
		System.setOut(console);
	}

}
