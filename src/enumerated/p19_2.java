package enumerated;

import java.util.Random;



enum CartoonCharacter2 {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

	private static Random rand = new Random();
	
	public static CartoonCharacter2 next() {
		// TODO 自动生成的方法存根
		return values()[rand.nextInt(values().length)];
	}
	
}
public class p19_2 {

	public static <T> void printNext(CartoonCharacter2 rg){
		System.out.print(rg.next() + ", ");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		CartoonCharacter2 cc = CartoonCharacter2.BOB;
		for(int i = 0; i < 10; i++){
			printNext(cc);
		}
	}

}

