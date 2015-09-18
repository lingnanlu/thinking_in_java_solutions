package enumerated;

import java.util.Arrays;
import java.util.Random;




import static io.lingnanlu.github.Print.*;



enum VowelsAndConsonants{
	VOWEL('a', 'e', 'i', 'o', 'u'),
	SOMETIMES_A_VOWEL('y', 'w'),
	CONSONANT('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 
			'p', 'q', 'r', 's', 't', 'v', 'x', 'z');
	
	private Character[] letters;
	private VowelsAndConsonants(Character... letters){
		this.letters = letters;
	}
	
	public static VowelsAndConsonants contains(Character c){
		if(Arrays.asList(VOWEL.letters).contains(c))
			return VOWEL;
		else if(Arrays.asList(SOMETIMES_A_VOWEL.letters).contains(c))
			return SOMETIMES_A_VOWEL;
		else 
			return CONSONANT;
	}
}

public class p19_5 {
	public static void main(String[] args) {
		Random rand = new Random(47);
		for (int i = 0; i < 100; i++) {
			int c = rand.nextInt(26) + 'a';
			printnb((char) c + ", " + c + ": ");
			print(VowelsAndConsonants.contains((char) c));
			EnumMap
		}
	}
}
