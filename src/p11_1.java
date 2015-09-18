import java.util.ArrayList;
import java.util.Iterator;

class Gerbil{
	private static int gerbilNumber;
	private int id = 0;
	Gerbil(){
		id = gerbilNumber++;
	}
	
	void hop(){
		System.out.println("id is " + id + " " + "jumping");
	}
	
}
public class p11_1 {
	public static void main(String[] args){
		ArrayList<Gerbil> gerbils = new ArrayList<Gerbil>();
		gerbils.add(new Gerbil());
		gerbils.add(new Gerbil());
		
		Iterator<Gerbil> it = gerbils.iterator();
		while(it.hasNext()){
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			Gerbil a = it.next();
			a.hop();
		}
	}
	
	
}
