package concurrency;
import static io.lingnanlu.github.Print.*;
import static java.util.concurrent.TimeUnit.*;

//: concurrency/GreenhouseScheduler.java
// Rewriting innerclasses/GreenhouseController.java
// to use a ScheduledThreadPoolExecutor.
// {Args: 5000}
import java.util.concurrent.*;
import java.util.*;

/*//利用DelayQueue自定义的ScheduledThreadPoolExecutor
class CustomScheduledThreadPoolExecutor {
	private DelayQueue<Delayed> delayQueue = new DelayQueue<Delayed>();
	
	public void schedule(Runnable event, long delay){
		
	}
	
	public 
}*/

abstract class Event implements Runnable, Delayed {
	protected final long delayTime;
	private long trigger;
	private long period;
	private boolean isRepeated = false;
	
	public Event(long delayTime) {
		this(delayTime, 0, false);
		
	}

	//java如何模拟参数的默认赋值 ，利用重载
	//这里的时间默认以Milliseconds为单位。
	public Event(long initialDelay, long period, boolean isRepeated){
		
		this.delayTime = initialDelay;
		this.isRepeated = isRepeated;
		this.period = period;
		
	}
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
	}

	public int compareTo(Delayed arg) {
		Event that = (Event) arg;
		if (trigger < that.trigger)
			return -1;
		if (trigger > that.trigger)
			return 1;
		return 0;
	}

	public void start() { // Allows restarting
		trigger = System.nanoTime()
				+ NANOSECONDS.convert(delayTime, MILLISECONDS);
	}
	
	public void restart() {
		trigger = System.nanoTime()
				+ NANOSECONDS.convert(period, MILLISECONDS);
	}

	public boolean isRepeated(){
		return isRepeated;
	}
	public abstract void run();
}

class Controller implements Runnable {
	private DelayQueue<Event> q;

	public Controller(DelayQueue<Event> q) {
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				
				//事件到期，取出
				Event event = q.take();
			
				
				//判断该Event是否是重复事件，
				//如果是，就将其加入到队列中去
				if(event.isRepeated()){
					event.restart();
					q.put(event);
				}
				
				print(event);
				event.run();
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		print("Finished Controller");
	}

	public void addEvent(Event c) {
		c.start();
		q.put(c);
	}
}

class GreenhouseControls extends Controller {
	
	public GreenhouseControls(DelayQueue<Event> q) {
		super(q);
	}

	private boolean light;

	public class LightOn extends Event {
		public LightOn(long delayTime) {
			super(delayTime);
		}
		
		public LightOn(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}

		public void run() {
			// Put hardware control code here to
			// physically turn on the light.
			light = true;
		}

		public String toString() {
			return "Light is on";
		}
	}

	public class LightOff extends Event {
		public LightOff(long delayTime) {
			super(delayTime);
		}
		
		public LightOff(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}


		public void run() {
			// Put hardware control code here to
			// physically turn off the light.
			light = false;
		}

		public String toString() {
			return "Light is off";
		}
	}

	private boolean water;

	public class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}

		
		public WaterOn(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}
		
		
		public void run() {
			// Put hardware control code here.
			water = true;
		}

		public String toString() {
			return "Greenhouse water is on";
		}
	}

	public class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}

		
		public WaterOff(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}
		
		public void run() {
			// Put hardware control code here.
			water = false;
		}

		public String toString() {
			return "Greenhouse water is off";
		}
	}

	private String thermostat = "Day";

	public class ThermostatNight extends Event {
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		public ThermostatNight(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}
		
		public void run() {
			// Put hardware control code here.
			thermostat = "Night";
		}

		public String toString() {
			return "Thermostat on night setting";
		}
	}

	public class ThermostatDay extends Event {
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		public ThermostatDay(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}
		
		
		public void run() {
			// Put hardware control code here.
			thermostat = "Day";
		}

		public String toString() {
			return "Thermostat on day setting";
		}
	}

	// An example of an action() that inserts a
	// new one of itself into the event list:
	public class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}

		
		public Bell(long initialDelay, long period, boolean isRepeated) {
			super(initialDelay, period, isRepeated);
		}
		
		public void run() {
			print(this);
		}

		public String toString() {
			return "Bing!";
		}
	}


	public class Terminate extends Event {
		private ExecutorService exec;

		public Terminate(long delayTime, ExecutorService e) {
			super(delayTime);
			exec = e;
		}

		public void run() {
			exec.shutdownNow();
		}

		public String toString() {
			return "Terminating";
		}
	}


}

public class p21_33b {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<Event> queue = new DelayQueue<Event>();
		GreenhouseControls gc = new GreenhouseControls(queue);
	
		gc.addEvent(gc.new Terminate(5000, exec));
		
		gc.addEvent(gc.new Bell(0, 1000, true));
		gc.addEvent(gc.new ThermostatNight(0, 2000, true));
		gc.addEvent(gc.new LightOn(0, 200, true));
		gc.addEvent(gc.new LightOff(0, 400, true));
		gc.addEvent(gc.new WaterOn(0, 600, true));
		gc.addEvent(gc.new WaterOff(0, 800, true));
		gc.addEvent(gc.new ThermostatDay(0, 1400, true));
		exec.execute(gc);
	}
}
