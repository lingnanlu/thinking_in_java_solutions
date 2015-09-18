class Toy{
	
}

class FancyToy extends Toy{
	
}


public class GenericToyTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		Class<FancyToy> ftClass = FancyToy.class;
		FancyToy ft = ftClass.newInstance();
		Class<? super FancyToy> up = ftClass.getSuperclass();
		//FancyToy another = up.newInstance();
		
		Class<? extends Toy> up2 = FancyToy.class;
		
		Toy toy = up2.newInstance();
	}

}
