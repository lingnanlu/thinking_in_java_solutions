
import java.util.*;


public class p11_17 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Map<String, Gerbil> gerbils = new HashMap<String, Gerbil>();
		gerbils.put("Fuzzy", new Gerbil());
		gerbils.put("Spot", new Gerbil());
		
		Iterator<String> it = gerbils.keySet().iterator();
		while(it.hasNext()){
			String name = it.next();
			gerbils.get(name).hop();
		}
	}

}
