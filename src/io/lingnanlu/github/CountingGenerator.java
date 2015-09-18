package io.lingnanlu.github;

public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean>{
		private boolean value = false;
		@Override
		public java.lang.Boolean next() {
			// TODO 自动生成的方法存根
			value = !value;
			return value;
		}
		
	}
	
	public static class Integer implements Generator<java.lang.Integer>{
		private int value = 0;
		@Override
		public java.lang.Integer next() {
			// TODO 自动生成的方法存根
			return value++;
		}
		
	}
	
	public static class Short implements Generator<java.lang.Short>{
		private short value = 0;
		@Override
		public java.lang.Short next() {
			// TODO 自动生成的方法存根
			return value++;
		}
		
	}
	
	public static class Long implements Generator<java.lang.Long>{
		private long value = 0;
		@Override
		public java.lang.Long next() {
			// TODO 自动生成的方法存根
			return value++;
		}
		
	}
	
	public static class Float implements Generator<java.lang.Float>{
		private float value = 0;
		@Override
		public java.lang.Float next() {
			// TODO 自动生成的方法存根	
			float result = value;
			value += 1.0;
			return result;
		}
	}
	
	public static class Double implements Generator<java.lang.Double>{
		private double value = 0;
		@Override
		public java.lang.Double next() {
			// TODO 自动生成的方法存根	
			double result = value;
			value += 1.0;
			return result;
		}
	}
	
	public static class Byte implements Generator<java.lang.Byte>{
		private byte value = 0;
		@Override
		public java.lang.Byte next() {
			// TODO 自动生成的方法存根	
			return value++;
		}
	}
	
	static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	
	public static class Character implements Generator<java.lang.Character>{
		int index = -1;
		@Override
		public java.lang.Character next() {
			// TODO 自动生成的方法存根
			index = (index + 1) % chars.length;
			return chars[index];
		}
		
	}
	
	public static class String implements Generator<java.lang.String>{
		private int length = 7;
		Generator<java.lang.Character> cg = new Character();
		public String(){}
		public String(int length){
			this.length = length;
		}
		
		@Override
		public java.lang.String next() {
			// TODO 自动生成的方法存根
			char[] buf = new char[length];
			for(int i = 0; i < length; i++){
				buf[i] = cg.next();
			}
			return new java.lang.String(buf);
		}
		
	}
}
