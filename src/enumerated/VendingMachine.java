package enumerated;

import static enumerated.Input.*;
import static io.lingnanlu.github.Print.*;
import io.lingnanlu.github.Generator;
import io.lingnanlu.github.TextFile;

import java.util.EnumMap;
import java.util.Iterator;
enum Category{
	
	MONEY(NICKEL, DIME, QUARTER, DOLLAR),
	ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
	QUIT_TRANSCTION(ABORT_TRANSCTION),
	SHUT_DOWN(STOP);
	
	private Input[] values;
	Category(Input... values){
		this.values = values;
	}
	
	private static EnumMap<Input, Category> categories = 
			new EnumMap<Input, Category>(Input.class);
	
	static{
		for(Category c : Category.class.getEnumConstants()){
			for(Input value : c.values){
				categories.put(value, c);
			}
		}
	}
	
	public static Category categorize(Input input){
		return categories.get(input);
	}
}


public class VendingMachine {

	private static State state = State.RESTING;
	private static int amount = 0;
	private static Input selection = null;
	
	enum StateDuration{
		TRANSIENT
	}
	enum State{
		RESTING{

			@Override
			void next(Input input) {
				// TODO 自动生成的方法存根
				switch(Category.categorize(input)){
				case MONEY:
					amount += input.amount();
					state = ADDINT_MONEY;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
					break;
				default:
				}
			}
			
		},
		
		ADDINT_MONEY{
			
			@Override
			void next(Input input) {
				// TODO 自动生成的方法存根
				switch(Category.categorize(input)){
				case MONEY:
					amount += input.amount();
					break;
				case ITEM_SELECTION:
					selection = input;
					if(amount < input.amount()){
						print("Insufficient money for " + selection);
					} else{
						state = DISPENSING;
					}
					break;
				case QUIT_TRANSCTION:
					state = GIVING_CHANGE;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:	
				}
				
			}
		},
		
		DISPENSING(StateDuration.TRANSIENT){
			void next(){
				print("here is your " + selection);
				amount -= selection.amount();
				state = GIVING_CHANGE;
			}
		},
		
		GIVING_CHANGE(StateDuration.TRANSIENT){
			void next(){
				if(amount > 0){
					print("Your change: " + amount);
					amount = 0;
				}
				state = RESTING;
			}
		},
		
		TERMINAL{
			void output(){
				print("Halted");
			}
		};
		
		private boolean isTransient = false;
		State(){}
		State(StateDuration trans){
			isTransient = true;
		}
		
		void output(){
			print(amount);
		}
		void next(Input input){
			throw new RuntimeException("Only call " + 
					"next(Input input) for non-transient states");
		}
		
		void next(){
			throw new RuntimeException("Only call " + 
					"next() for StateDuration.TRANSIENT states");
		}
		
		
		
	}
	
	
	static void run(Generator<Input> gen){
		while(state != State.TERMINAL){
			state.next(gen.next());
			while(state.isTransient)
				state.next();
			state.output();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Generator<Input> gen = new RandomInputGenerator();
		if(args.length == 1){
			gen = new FileInputGenerator(args[0]);
		}
		run(gen);
	}

}


class RandomInputGenerator implements Generator<Input>{

	@Override
	public Input next() {
		// TODO 自动生成的方法存根
		return Input.randomSelection();
	}
	
}

class FileInputGenerator implements Generator<Input>{

	private Iterator<String> it;
	public FileInputGenerator(String fileName){
		it = new TextFile(fileName, ";").iterator();
	}
	@Override
	public Input next() {
		// TODO 自动生成的方法存根
		if(!it.hasNext())
			return null;
		return Enum.valueOf(Input.class, it.next().trim());
	}
	
}