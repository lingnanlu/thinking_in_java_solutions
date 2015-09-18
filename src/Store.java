import generics.Generators;

import java.util.*;

import net.mindview.util.Generator;

class Product{
	private final int id;
	private String description;
	private double price;
	public Product(int IDnumber, String descr, double price){
		id = IDnumber;
		description = descr;
		this.price = price;
	}
	
	public String toString(){
		return id + ": " + description + ", price: $" + price;
	}
	
	public static Generator<Product> generator = 
			new Generator<Product>(){
				private Random rand = new Random();
				@Override
				public Product next() {
					// TODO 自动生成的方法存根
					return new Product(rand.nextInt(1000),
							"Test", 
							Math.round(rand.nextDouble() *1000.0) + 0.99);
				}
	};
}

class Shelf extends ArrayList<Product>{
	public Shelf(int nProducts){
		Generators.fill(this, Product.generator, nProducts);
	}
}

class Aisle extends ArrayList<Shelf>{
	public Aisle(int nShelves, int nProducts){
		for(int i = 0; i < nShelves; i++){
			this.add(new Shelf(nProducts));
		}
	}
}

class CheckoutStand{
	
}

class Office{}


public class Store extends ArrayList<Aisle>{

	public Store(int nAisles, int nShelves, int nProducts){
		for(int i = 0; i < nAisles; i++){
			this.add(new Aisle(nShelves, nProducts));
		}
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		for(Aisle a : this){
			for(Shelf s : a){
				for(Product p : s){
					result.append(p);
					result.append("\n");
				}
				result.append("\n");
			}
			result.append("\n");
		}
		return result.toString();
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println(new Store(3, 5, 10));
	}

}
