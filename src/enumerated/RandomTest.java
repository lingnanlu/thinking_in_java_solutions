package enumerated;

import io.lingnanlu.github.Enums;


enum Activity {
	SITTING, LYING, STANDING, HOPPING, 
	RUNNING, DODGING
}
public class RandomTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		for(int i = 0; i < 20; i++){
			System.out.print(Enums.random(Activity.class));
		}
	}

}
