import java.util.*;
import static io.lingnanlu.github.Print.*;
public class p11_19 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String[] names = {"Fuzzy", "Spot", "Speedy", "Dopey", "Sleepy", "Happy", "Funny", "Silly"};
		
		Map<String, Gerbil> gerbils = new HashMap<String, Gerbil>();
		for(String name : names){
			gerbils.put(name, new Gerbil());
		}
		
		print(gerbils);
		print();
		
		Set<String> hashedName = new HashSet<String>(gerbils.keySet());
		
		print(hashedName);
		
		Map<String, Gerbil> sortedGerbils = new LinkedHashMap<String, Gerbil>();
		
		for(String name : hashedName){
			sortedGerbils.put(name, gerbils.get(name));
		}
		
		print(sortedGerbils);
		
		Set<String> linkhashedName = new LinkedHashSet<String>(gerbils.keySet());
		
		print(linkhashedName);
		
		Map<String, Gerbil> anothersortedGerbils = new LinkedHashMap<String, Gerbil>();
		
		for(String name : linkhashedName){
			anothersortedGerbils.put(name, gerbils.get(name));
		}
		
		print(anothersortedGerbils);
	}

}


