package io;

import java.io.*;
public class p18_21 {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		BufferedInputStream in = new BufferedInputStream(
				new FileInputStream("./io/p18_21.java"));
		
		
		//�ض���ֻ�ܲ����ֽ���
		System.setIn(in);
		
		
		
		//�����Ҫ�ַ�������Ҫ����Adapter���ֽ���ת��Ϊ�ַ���
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		String s;
		while((s = br.readLine()) != null){
			System.out.print(s.toUpperCase());
		}
		
	}

}
