import java.util.*;
import static io.lingnanlu.github.Print.*;
public class p11_18 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String[] names = {"Fuzzy", "Spot", "Speedy", "Dopey", "Sleepy", "Happy", "Funny", "Silly"};
		
		Map<String, Gerbil> gerbils = new HashMap<String, Gerbil>();
		for(String name : names){
			gerbils.put(name, new Gerbil());
		}
		
		print(gerbils);
		print();
		
		TreeSet<String> sortedName = new TreeSet<String>(gerbils.keySet());
		
		print(sortedName);
		
		Map<String, Gerbil> sortedGerbils = new LinkedHashMap<String, Gerbil>();
		
		for(String name : sortedName){
			sortedGerbils.put(name, gerbils.get(name));
		}
		
		print(sortedGerbils);
	}

}
