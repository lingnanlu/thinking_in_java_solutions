package typeinfo.pets;

import java.util.*;

public class ForNameCreator extends PetCreator{


	private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
	
	private static String[] typeNames = {
		"typeinfo.pets.Mutt",
		"typeinfo.pets.Pug",
		"typeinfo.pets.EgyptianMau",
		"typeinfo.pets.Manx",
		"typeinfo.pets.Cymric",
		"typeinfo.pets.Rat",
		"typeinfo.pets.Mouse",
		"typeinfo.pets.Hamster",
	};
	
	@SuppressWarnings("unchecked")
	private static void loader(){
		for(String name : typeNames){
			try {
				types.add((Class<? extends Pet>) Class.forName(name));
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				throw new RuntimeException(e);
			}
		}
	}
	static {
		loader();
	}
	@Override
	public List<Class<? extends Pet>> types() {
		// TODO 自动生成的方法存根
		return types;
	}
	

}
