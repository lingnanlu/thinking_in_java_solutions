package enumerated;
import static io.lingnanlu.github.Print.*;

import java.util.EnumSet;

public class CarWash {
	public enum Cycle{
		UNDERBODY{

			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Spraying the underbody");
			}
			
		}, 
		
		WHEELWASH {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Washing the wheels");
			}
		},
		PREWASH {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Loosening the dirt");
			}
		},
		BASIC {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Applying hot wax");
			}
		},
		HOTWAX {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Rinsing");
			}
		},
		RINSE {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("Blowing dry");
			}
		},
		BLOWDRY {
			@Override
			void action() {
				// TODO �Զ����ɵķ������
				print("The basic wash");
			}
		};
		abstract void action();
	}
	
	
	EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);
	
	public void add(Cycle cycle){
		cycles.add(cycle);
	}
	
	public void washCar(){
		for(Cycle c : cycles){
			c.action();
		}
	}
	
	public String toString(){
		return cycles.toString();
	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		CarWash wash = new CarWash();
		print(wash);
		wash.washCar();
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.RINSE);;
		wash.add(Cycle.HOTWAX);
		print(wash);
		wash.washCar();
	}

}
