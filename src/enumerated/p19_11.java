package enumerated;

import static io.lingnanlu.github.Print.print;
import io.lingnanlu.github.Generator;
import io.lingnanlu.github.TextFile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface VendingInput{
	String name();
}

class MoneyUnit implements VendingInput{
	private String name;
	private int amount;
	public MoneyUnit(String name, int amount){
		this.name = name;
		this.amount = amount;
	}
	
	public int amount(){
		return amount;
	}

	@Override
	public String name() {
		// TODO 自动生成的方法存根
		return name;
	}
}

class VendedItem implements VendingInput{
	private String name;
	private int price;
	public VendedItem(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public int price(){
		return price;
	}

	@Override
	public String name() {
		// TODO 自动生成的方法存根
		return name;
	}
	
	public String toString(){
		return name + " : " + price;
	}
}

class VendingEvent implements VendingInput{
	private String name;
	public VendingEvent(String name){
		this.name = name;
	}
	@Override
	public String name() {
		// TODO 自动生成的方法存根
		return name;
	}

}

public class p19_11 {

	private static State state = State.RESTING;
	private static int amount = 0;
	private static VendedItem selection = null;
	
	enum StateDuration{
		TRANSIENT
	}
	enum State{
		RESTING{

			@Override
			void next(VendingInput input) {
				// TODO 自动生成的方法存根
				if(input instanceof MoneyUnit){
					amount += ((MoneyUnit)input).amount();
					state = ADDINT_MONEY;
					return ;
				}
				
				if(input instanceof VendingEvent){
					if(((VendingEvent)input).equals("stop")){
						state = TERMINAL;
						return ;
					}
				}
				return ;
			}
			
		},
		
		ADDINT_MONEY{
			
			@Override
			void next(VendingInput input) {
				// TODO 自动生成的方法存根
				if(input instanceof MoneyUnit){
					amount += ((MoneyUnit)input).amount();
					return ;
				}
				
				if (input instanceof VendedItem){
					selection = (VendedItem) input;
					if(amount < selection.price()){
						print("Insufficient money for " + selection);
					} else{
						state = DISPENSING;
					}
					return ;
				}
				
				
				if (input instanceof VendingEvent){
					if(((VendingEvent)input).equals("stop")){
						state = TERMINAL;
						return ;
					}
					
					if(((VendingEvent)input).equals("abortTransaction")){
						state = GIVING_CHANGE;
						return ;
					}
				}
				
				return ;
				
			}
		},
		
		DISPENSING(StateDuration.TRANSIENT){
			void next(){
				print("here is your " + selection);
				amount -= selection.price();
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
		void next(VendingInput input) {
			// TODO 自动生成的方法存根
			throw new RuntimeException("Only call " + 
					"next(Input input) for non-transient states");
		}
		State(StateDuration trans){
			isTransient = true;
		}
		
		void output(){
			print(amount);
		}
		
		void next(){
			throw new RuntimeException("Only call " + 
					"next() for StateDuration.TRANSIENT states");
		}
		
		
		
	}
	
	
	static void run(Generator<VendingInput> gen){
		while(state != State.TERMINAL){
			state.next(gen.next());
			while(state.isTransient)
				state.next();
			state.output();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		Generator<VendingInput> gen = new RandomInputGenerator();
		if(args.length == 1){
			gen = new FileInputGenerator(args[0]);
		}
		run(gen);
		
		
	/*	for(int i = 0; i < 30; i++){
			print(gen.next().name());
		}*/
	}

}


class RandomInputGenerator implements Generator<VendingInput>{

	private static List<List<VendingInput>> vendingInputList = 
			new ArrayList<List<VendingInput>>();
	
	private static MoneyUnit[] moneyUnitArray = {
		new MoneyUnit("Nickel", 5),
		new MoneyUnit("Dime", 10),
		new MoneyUnit("Quarter", 25),
		new MoneyUnit("Dollar", 100),

	};
	
	private static VendedItem[] vendingItemArray = {
		new VendedItem("Soda", 100),
		new VendedItem("Juice", 125),
		new VendedItem("HotChocolate", 100),
		new VendedItem("Coffee", 145),
		new VendedItem("CandyBar", 90),
	};
	
	private static VendingEvent[] vendingEvent = {
		new VendingEvent("abortTransaction"),
		new VendingEvent("stop"),

	};
	static {
		vendingInputList.add(Arrays.asList(moneyUnitArray));
		vendingInputList.add(Arrays.asList(vendingItemArray));
		vendingInputList.add(Arrays.asList(vendingEvent));
	}
	
	private Random rand = new Random(47);
	@Override
	public VendingInput next() {
		// TODO 自动生成的方法存根
		List<VendingInput> list = vendingInputList.get(rand.nextInt(vendingInputList.size()));
		return list.get(rand.nextInt(list.size()));
	}
	
}

class FileInputGenerator implements Generator<VendingInput>{
	
	private List<MoneyUnit> moneyUnitList = new ArrayList<MoneyUnit>();
	private List<VendedItem> vendedItemList = new ArrayList<VendedItem>();
	private List<VendingEvent> vendingEventList = new ArrayList<VendingEvent>();

	
	public FileInputGenerator(String fileName) throws IOException{
		BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName))
				);
		
		String s;
		s = in.readLine();
		System.out.println("--------------moneyUnitList--------------");
		for(String item : s.split(",")){
			System.out.print(item + " ");
			int index = item.indexOf("(");
			String name = item.substring(0, index);
			int amount = Integer.parseInt(item.substring(index + 1, item.length() - 1));
			moneyUnitList.add(new MoneyUnit(name, amount));
		}
		
		System.out.println();
		
		System.out.println("--------------vendedItemList--------------");
		s = in.readLine();
		for(String item : s.split(",")){
			System.out.print(item + " ");
			int index = item.indexOf("(");
			String name = item.substring(0, index);
			int price = Integer.parseInt(item.substring(index + 1, item.length() - 1));
			vendedItemList.add(new VendedItem(name, price));
		}
		
		System.out.println();
		
		System.out.println("--------------vendingEventList--------------");
		s = in.readLine();
		for(String item : s.split(",")){
			System.out.print(item + " ");
			vendingEventList.add(new VendingEvent(item));
		}
		
		System.out.println();
	}
	
	private Random rand = new Random(47);
	
	@Override
	public VendingInput next() {
		
		int randInt = rand.nextInt(3);
		
		if(randInt == 0){
			return moneyUnitList.get(rand.nextInt(moneyUnitList.size()));
		} else if (randInt == 1){
			return vendedItemList.get(rand.nextInt(vendedItemList.size()));
		} else{
			return vendingEventList.get(rand.nextInt(vendingEventList.size()));
		}
		
	}
	
}