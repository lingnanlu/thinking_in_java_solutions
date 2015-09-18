package enumerated;

import java.util.Random;

import io.lingnanlu.github.Generator;

enum CartoonCharacter implements Generator<CartoonCharacter>{
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

	private Random rand = new Random();
	@Override
	public CartoonCharacter next() {
		// TODO �Զ����ɵķ������
		return values()[rand.nextInt(values().length)];
	}
	
}
public class EnumImplementation {

	public static <T> void printNext(Generator<T> rg){
		System.out.print(rg.next() + ", ");
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i = 0; i < 10; i++){
			printNext(cc);
		}
	}

}
