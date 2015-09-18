package io;

import java.io.*;
import java.util.*;
import static io.lingnanlu.github.Print.*;

class Data implements Serializable {
	private int n;

	public Data(int n) {
		this.n = n;
	}

	public String toString() {
		return Integer.toString(n);
	}
}

public class Worm implements Serializable {
	private static Random rand = new Random(47);
	private Data[] d = { new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)), new Data(rand.nextInt(10)) };
	private Worm next;
	private char c;

	// Value of i == number of segments
	public Worm(int i, char x) {
		print("Worm constructor: " + i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}

	public Worm() {
		print("Default constructor");
	}

	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for (Data dat : d)
			result.append(dat);
		result.append(")");
		if (next != null)
			result.append(next);
		return result.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		Worm w = new Worm(5, 'a');
		print("w = " + w);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
		
		out.writeObject(w);
		
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
		
		Worm nw = (Worm)in.readObject();
		
		print("nw = " + nw);
	}
}