package enumerated;
import static io.lingnanlu.github.Print.*;

import java.util.EnumSet;

public class CarWash {
	public enum Cycle{
		UNDERBODY{

			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Spraying the underbody");
			}
			
		}, 
		
		WHEELWASH {
			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Washing the wheels");
			}
		},
		PREWASH {
			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Loosening the dirt");
			}
		},
		BASIC {
			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Applying hot wax");
			}
		},
		HOTWAX {
			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Rinsing");
			}
		},
		RINSE {
			@Override
			void action() {
				// TODO 自动生成的方法存根
				print("Blowing dry");
			}
		},
		BLOWDRY {
			@Override
			void action() {
				// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
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
