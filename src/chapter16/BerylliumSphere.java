package chapter16;
import java.util.Comparator;

import io.lingnanlu.github.*;
public class BerylliumSphere {
	private static long counter;
    long id = counter++;
	public String toString(){
		return "Sphere " + id;
	}
	
	public static Generator<BerylliumSphere> generator(){
		return new Generator<BerylliumSphere>(){

			@Override
			public BerylliumSphere next() {
				// TODO �Զ����ɵķ������
				return new BerylliumSphere();
			}
			
		};
	}
	public void change(){
		id++;
	}



}
