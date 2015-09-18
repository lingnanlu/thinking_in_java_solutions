package enumerated;

import static io.lingnanlu.github.Print.print;

import java.util.EnumMap;

import io.lingnanlu.github.Generator;


public class p19_10 {

	private  State state = State.RESTING;
	private  int amount = 0;
	private  Input selection = null;
	private  static int count = 0;
	private  final int id = count++;
	enum StateDuration{
		TRANSIENT
	}
	
	public p19_10(){
		print("id " + id);
	}
	class Command{
		void next(Input input){
			throw new RuntimeException("Only call " + 
					"next(Input input) for non-transient states");
		}
		
		void next(){
			throw new RuntimeException("Only call " + 
					"next() for StateDuration.TRANSIENT states");
		}
		
		void output(){
			print(amount);
		}
	}
	
	private EnumMap<State, Command> stateActions = 
			new EnumMap<State, Command>(State.class);
	
	
	{
		stateActions.put(State.RESTING, new Command(){

			@Override
			public void next(Input input) {
				// TODO �Զ����ɵķ������
				switch(Category.categorize(input)){
				case MONEY:
					amount += input.amount();
					state = State.ADDINT_MONEY;
					break;
				case SHUT_DOWN:
					state = State.TERMINAL;
					break;
				default:
				}
			}
			
		});
		
		
		stateActions.put(State.ADDINT_MONEY, new Command(){

			@Override
			public void next(Input input) {
				// TODO �Զ����ɵķ������
				switch(Category.categorize(input)){
				case MONEY:
					amount += input.amount();
					break;
				case ITEM_SELECTION:
					selection = input;
					if(amount < input.amount()){
						print("Insufficient money for " + selection);
					} else{
						state = State.DISPENSING;
					}
					break;
				case QUIT_TRANSCTION:
					state = State.GIVING_CHANGE;
					break;
				case SHUT_DOWN:
					state = State.TERMINAL;
				default:	
				}
			}
			
		});
		
		stateActions.put(State.DISPENSING, new Command(){

			@Override
			public void next(){
				print("here is your " + selection);
				amount -= selection.amount();
				state = State.GIVING_CHANGE;
			}
			
		});
		
		
		stateActions.put(State.GIVING_CHANGE, new Command(){

			@Override
			public void next(){
				if(amount > 0){
					print("Your change: " + amount);
					amount = 0;
				}
				state = State.RESTING;
			}
			
		});
		
		
		stateActions.put(State.TERMINAL, new Command(){

			@Override
			public void output(){
				print("Halted");
			}
			
		});
	}

	
	
	enum State{
		RESTING,
		ADDINT_MONEY,
		DISPENSING(StateDuration.TRANSIENT),
		GIVING_CHANGE(StateDuration.TRANSIENT),
		TERMINAL;
		private boolean isTransient = false;
		State(){}
		State(StateDuration trans){
			isTransient = true;
		}
			
	}
	
	
	void run(Generator<Input> gen){
		Command command;
		while(state != State.TERMINAL){
			command = stateActions.get(state);
			
			/*
			 * ��ʹ�ó�����ط���ʱ����״̬�ı������Ӧ�ķ���Ҳ��ı䣬
			 * ��ʹ��EnumMap�Ļ�����״̬�ı������Ӧ�ķ�����û�иı䣬
			 * ���Ե��˹��ı��䷽����
			 */
			command.next(gen.next());
			command = stateActions.get(state);
			while(state.isTransient){
				command.next();
				command = stateActions.get(state);

			}
				
			
			command.output();
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Generator<Input> gen = new RandomInputGenerator();
		if(args.length == 1){
			gen = new FileInputGenerator(args[0]);
		}
		
		new p19_10().run(gen);
		
		if(args.length == 1){
			gen = new FileInputGenerator(args[0]);
		}
		new p19_10().run(gen);
	}

}
