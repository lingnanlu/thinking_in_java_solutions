package io.lingnanlu.github;

import java.util.Random;

import io.lingnanlu.github.RandomGenerator.Character;

public class RandomGenerator {
	private static Random r = new Random(47);
	
	public static class Boolean implements Generator<java.lang.Boolean>{
		@Override
		public java.lang.Boolean next() {
			return r.nextBoolean();
		}
		
	}
	
	public static class Integer implements Generator<java.lang.Integer>{
		private int mod = 10000;
		public Integer() {}
		public Integer(int modulo){
			mod = modulo;
		}
		@Override
		public java.lang.Integer next() {
			// TODO 自动生成的方法存根
			return r.nextInt(mod);
		}
		
	}
	
	public static class Short implements Generator<java.lang.Short>{
		@Override
		public java.lang.Short next() {
			// TODO 自动生成的方法存根
			return (short)r.nextInt();
		}
		
	}
	
	public static class Long implements Generator<java.lang.Long>{
		private int mod = 10000;
		public Long() {}
		public Long(int modulo){
			mod = modulo;
		}
		@Override
		public java.lang.Long next() {
			// TODO 自动生成的方法存根
			return (long) r.nextInt(mod);
		}
		
	}
	
	public static class Float implements Generator<java.lang.Float>{
		@Override
		public java.lang.Float next() {
			// TODO 自动生成的方法存根	
			int trimmed = Math.round(r.nextFloat() * 100);
			return ((float)trimmed) / 100;
		}
	}
	
	public static class Double implements Generator<java.lang.Double>{
		private double value = 0;
		@Override
		public java.lang.Double next() {
			// TODO 自动生成的方法存根	
			long trimmed = Math.round(r.nextDouble() * 100);
			return ((double)trimmed) / 100;
		}
	}
	
	public static class Byte implements Generator<java.lang.Byte>{
		@Override
		public java.lang.Byte next() {
			// TODO 自动生成的方法存根	
			return (byte)r.nextInt();
		}
	}
	
	public static class Character implements Generator<java.lang.Character>{
		@Override
		public java.lang.Character next() {
			// TODO 自动生成的方法存根
			return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
		}
		
	}
	
	public static class String extends CountingGenerator.String{
		{
			cg = new Character();
		}
		
		public String(){}
		public String(int length){
			super(length);
		}
	}
}
