//: holding/NonCollectionSequence.java
import typeinfo.pets.*;

import java.util.*;

class PetSequence {
  protected Pet[] pets = Pets.createArray(8);
}

class NonCollectionSequence extends PetSequence implements Iterable<Pet> {
  public Iterator<Pet> iterator() {
    return new Iterator<Pet>() {
      private int index = 0;
      public boolean hasNext() {
        return index < pets.length;
      }
      public Pet next() { return pets[index++]; }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public Iterable<Pet> reversed(){
	  return new Iterable<Pet>(){
		@Override
		public Iterator<Pet> iterator() {
			// TODO 自动生成的方法存根
			return new Iterator<Pet>(){
				private int index = pets.length - 1;
				@Override
				public boolean hasNext() {
					// TODO 自动生成的方法存根
					return index > -1;
				}

				@Override
				public Pet next() {
					// TODO 自动生成的方法存根
					return pets[index--];
				}
				
			};
		}
	  };
  }
  
  public Iterable<Pet> randomed(){
	  return new Iterable<Pet>(){
		@Override
		public Iterator<Pet> iterator() {
			// TODO 自动生成的方法存根
			List<Pet> shuffed = new ArrayList<Pet>(Arrays.asList(pets));
			Collections.shuffle(shuffed, new Random(47));
			return shuffed.iterator();
		}
	  };
  }
  
} 
public class p11_32 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		NonCollectionSequence ncs = new NonCollectionSequence();
		
		for(Pet p : ncs){
			System.out.println(p.id() + ": " + p);
		}
		
		System.out.println();
		for(Pet p : ncs.reversed()){
			System.out.println(p.id() + ": " + p);
		}
		
		
		System.out.println();
		for(Pet p : ncs.randomed()){
			System.out.println(p.id() + ": " + p);
		}
	}

}
