package containers;

import io.lingnanlu.github.*;
import io.lingnanlu.github.CountingGenerator.Character;
import static io.lingnanlu.github.Print.*;

import java.util.Iterator;



class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer>{
	private int size = 9;
	private int number = 1;
	private char letter = 'A';
	
	@Override
	public Iterator<Integer> iterator() {
		// TODO 自动生成的方法存根
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				// TODO 自动生成的方法存根
				return number < size;
			}

			@Override
			public Integer next() {
				// TODO 自动生成的方法存根
				return number++;
			}
			
		};
	}
	@Override
	public Pair<Integer, String> next() {
		// TODO 自动生成的方法存根
		return new Pair<Integer, String>(
				number++, "" + letter++);
	}
	
}
public class MapDataTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		print(MapData.map(new Letters(), 11));
		print(MapData.map(new CountingGenerator.Character(), new RandomGenerator.String(3), 8));
		print(MapData.map(new CountingGenerator.Character(), "value", 6));
		print(MapData.map(new Letters(), new RandomGenerator.String(3)));
		print(MapData.map(new Letters(), "Pop"));
	}

}
