package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FreezeAlien {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO �Զ����ɵķ������
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("X.file"));
		Alien quellek = new Alien();
		out.writeObject(quellek);
	}

}
