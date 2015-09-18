package io;

//: io/RecoverCADState.java
// Restoring the state of the pretend CAD system.
// {RunFirst: StoreCADState}
import java.io.*;
import java.util.*;

public class RecoverCADState {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"CADState.out"));
		// Read in the same order they were written:
		Line.deserializeStaticState(in);
		Square.deserializeStaticState(in);
		Circle.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject();
		System.out.println(shapes);
	}
} 
