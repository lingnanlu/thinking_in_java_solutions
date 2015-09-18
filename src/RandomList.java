import java.util.ArrayList;
import java.util.Random;
import typeinfo.pets.*;

public class RandomList<T> {
	private ArrayList<T> storage = new ArrayList<T>();
	private Random rand = new Random(47);
	public void add(T element){
		storage.add(element);
	}
	
	public T select(){
		return storage.get(rand.nextInt(storage.size()));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		RandomList<Pet> pets = new RandomList<Pet>();
		for(Pet pet : Pets.createArray(10)){
			pets.add(pet);
		}
		
		for(int i = 0; i < 10; i++){
			System.out.println(pets.select());
		}
	}

}
