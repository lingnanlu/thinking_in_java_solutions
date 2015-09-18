package io.lingnanlu.github;

public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean>{
		private boolean value = false;
		@Override
		public java.lang.Boolean next() {
			// TODO �Զ����ɵķ������
			value = !value;
			return value;
		}
		
	}
	
	public static class Integer implements Generator<java.lang.Integer>{
		private int value = 0;
		@Override
		public java.lang.Integer next() {
			// TODO �Զ����ɵķ������
			return value++;
		}
		
	}
	
	public static class Short implements Generator<java.lang.Short>{
		private short value = 0;
		@Override
		public java.lang.Short next() {
			// TODO �Զ����ɵķ������
			return value++;
		}
		
	}
	
	public static class Long implements Generator<java.lang.Long>{
		private long value = 0;
		@Override
		public java.lang.Long next() {
			// TODO �Զ����ɵķ������
			return value++;
		}
		
	}
	
	public static class Float implements Generator<java.lang.Float>{
		private float value = 0;
		@Override
		public java.lang.Float next() {
			// TODO �Զ����ɵķ������	
			float result = value;
			value += 1.0;
			return result;
		}
	}
	
	public static class Double implements Generator<java.lang.Double>{
		private double value = 0;
		@Override
		public java.lang.Double next() {
			// TODO �Զ����ɵķ������	
			double result = value;
			value += 1.0;
			return result;
		}
	}
	
	public static class Byte implements Generator<java.lang.Byte>{
		private byte value = 0;
		@Override
		public java.lang.Byte next() {
			// TODO �Զ����ɵķ������	
			return value++;
		}
	}
	
	static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	
	public static class Character implements Generator<java.lang.Character>{
		int index = -1;
		@Override
		public java.lang.Character next() {
			// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
			char[] buf = new char[length];
			for(int i = 0; i < length; i++){
				buf[i] = cg.next();
			}
			return new java.lang.String(buf);
		}
		
	}
}
