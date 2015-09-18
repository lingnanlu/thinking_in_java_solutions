import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.*;

class MovieGenerator{
	private int current = 0;
	private String[] movies = {"Snow White", "Star Wars", "Star Treks", "Big Bang"};
	private Random rand = new Random(47);
	public String next(){
		String movie = movies[current];
		current = (current + 1) % movies.length;
		return movie;
	}
}
public class p11_4 {
	public static Collection fillCollection(Collection<String> collection, 
			MovieGenerator gen){
		collection.add(gen.next());
		collection.add(gen.next());
		collection.add(gen.next());
		collection.add(gen.next());
		return collection;
	}
	
	public static void main(String[] args){
		MovieGenerator gen = new MovieGenerator();
		System.out.println(fillCollection(new ArrayList(), gen));
		System.out.println(fillCollection(new LinkedList(), gen));
		System.out.println(fillCollection(new HashSet(), gen));
		System.out.println(fillCollection(new LinkedHashSet(), gen));
		System.out.println(fillCollection(new TreeSet(), gen));
	}
}
