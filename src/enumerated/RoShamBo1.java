package enumerated;

import static enumerated.Outcome.*;

import java.util.Random;



interface Item{
	Outcome compete(Item it);
	Outcome eval(Paper p);
	Outcome eval(Scissors s);
	Outcome eval(Rock r);
}

class Paper implements Item{

	@Override
	public Outcome compete(Item it) {
		// TODO �Զ����ɵķ������
		return it.eval(this);
	}

	@Override
	public Outcome eval(Paper p) {
		// TODO �Զ����ɵķ������
		return DRAW;
	}

	@Override
	public Outcome eval(Scissors s) {
		// TODO �Զ����ɵķ������
		return WIN;
	}

	@Override
	public Outcome eval(Rock r) {
		// TODO �Զ����ɵķ������
		return LOSE;
	}
	
	public String toString(){
		return "Paper";
	}
	
}

class Scissors implements Item{

	@Override
	public Outcome compete(Item it) {
		// TODO �Զ����ɵķ������
		return it.eval(this);
	}

	@Override
	public Outcome eval(Paper p) {
		// TODO �Զ����ɵķ������
		return LOSE;
	}

	@Override
	public Outcome eval(Scissors s) {
		// TODO �Զ����ɵķ������
		return DRAW;
	}

	@Override
	public Outcome eval(Rock r) {
		// TODO �Զ����ɵķ������
		return WIN;
	}
	
	public String toString(){
		return "Scissors";
	}
}

class Rock implements Item{

	@Override
	public Outcome compete(Item it) {
		// TODO �Զ����ɵķ������
		return it.eval(this);
	}

	@Override
	public Outcome eval(Paper p) {
		// TODO �Զ����ɵķ������
		return WIN;
	}

	@Override
	public Outcome eval(Scissors s) {
		// TODO �Զ����ɵķ������
		return LOSE;
	}

	@Override
	public Outcome eval(Rock r) {
		// TODO �Զ����ɵķ������
		return DRAW;
	}
	
	public String toString(){
		return "Rock";
	}
	
}
public class RoShamBo1 {

	static final int SIZE = 20;
	static Random rand = new Random(47);
	public static Item newItem(){

		switch(rand.nextInt(3)){
		default:
		case 0:return new Paper();
		case 1:return new Scissors();
		case 2:return new Rock();
		}

	}
	public static void match(Item a, Item b){
		System.out.println(a + " vs " + b + " : " + a.compete(b));
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		for(int i = 0; i < SIZE; i++){
			match(newItem(), newItem());
		}
	}

}
