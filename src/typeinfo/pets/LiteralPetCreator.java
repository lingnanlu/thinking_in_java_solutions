package typeinfo.pets;
import java.util.*;

public class LiteralPetCreator extends PetCreator{

	public static final List<Class<? extends Pet>> allTypes =
			Collections.unmodifiableList(Arrays.asList(Cat.class,
					Cymric.class, Dog.class, EgyptianMau.class,
					Hamster.class, Manx.class, Mouse.class, 
					Mutt.class, Pet.class, Pug.class, Rat.class,Rodent.class));
	
	private static final List<Class<? extends Pet>> types = 
			allTypes.subList(allTypes.indexOf(Mutt.class), allTypes.size());
	
	@Override
	public List<Class<? extends Pet>> types() {
		// TODO 自动生成的方法存根
		return types;
	}
	
}