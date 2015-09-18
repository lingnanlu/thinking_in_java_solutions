import generics.Generators;

import java.util.*;

import net.mindview.util.*;

class BigFish{
	private static long counter = 1;
	private final long id = counter++;
	private BigFish(){
		
	}
	public static Generator<BigFish> generator = new Generator<BigFish>(){
			@Override
			public BigFish next() {
				// TODO 自动生成的方法存根
				return new BigFish();
			}
			
	};
	
	public String toString(){
		return "BigFish " + id;
	}
	
}

class LittleFish{
	private static long counter = 1;
	private final long id = counter++;
	private LittleFish(){}
	
	public static Generator<LittleFish> generator = 
			new Generator<LittleFish>(){
				@Override
				public LittleFish next() {
					// TODO 自动生成的方法存根
					return new LittleFish();
				}
	};
	
	public String toString(){
		return "LittleFish " + id;
	}
}
public class p15_18 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Random rand = new Random(47);
		Queue<LittleFish> line = new LinkedList<LittleFish>();
		Generators.fill(line, LittleFish.generator, 20);
		List<BigFish> bigfishs = new ArrayList<BigFish>();
		Generators.fill(bigfishs, BigFish.generator, 5);
		for(LittleFish lf : line){
			System.out.println(bigfishs.get(rand.nextInt(bigfishs.size())) + " eat " + lf);
		}
	}

}
