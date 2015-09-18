package io.lingnanlu.github;

import java.io.*;

public class BinaryFile {
	public static byte[] read(File bFile) throws IOException {

		//当文件不存在时，此时并不需要关闭文件，因为文件就没打开
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(
				bFile));

		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}

	}

	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}

	public static void main(String[] args) throws IOException {
		System.out.println(read("hehehehehehh.class"));
	}
}
