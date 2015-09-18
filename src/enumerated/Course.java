package enumerated;

import io.lingnanlu.github.Enums;

public enum Course {

	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);
	
	private Food[] values;
	
	private Course(Class<? extends Food> kind){
		values = kind.getEnumConstants();
	}
	
	interface Food {
		enum Appetizer implements Food{
			SALAD, SOUP, SPRING_ROLLS
		}

		enum MainCourse implements Food{
			LASAGNE, BURRITO, PAD_THAI,
			LENTILS, HUMMOUS, VINDALOO;
		}

		enum Dessert implements Food{
			TIRAMISU, GELATO, BLACK_FOREST_CAKE,
			FRUIT, CREME_CARAMEL;
		}

		enum Coffee implements Food{
			BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
			LATTE, CAPPUCCINO, TEA, HERB_TEA
		}
	}
	
	public Food randomSelection(){
		return Enums.random(values);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(int i = 0; i < 5; i++){
			for(Course course : Course.values()){
				System.out.println(course.randomSelection());
			}
			
			System.out.println("-------------------");
		}
	}

}
