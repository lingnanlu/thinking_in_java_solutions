package concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;


class Car2 {
	private final int id;
	private boolean
			engine = false, driveTrain = false, wheels = false;
	
	public Car2(int idn) {
		id = idn;
	}
	
	public Car2() { id = -1; }
	
	public synchronized int getId() {
		return id;
	}
	
	public synchronized void addEngine() {
		engine = true;
	}
	
	public synchronized void addDriveTrain() {
		driveTrain = true;
	}
	
	public synchronized void addWheels() {
		wheels = true;
	}
	
	public synchronized String toString() {
		return "Car " + id + " [" + " engine: " + engine + " driveTrain: " + 
	driveTrain + " wheels: " + wheels + " ]";
	}
}


class CarQueue extends LinkedBlockingQueue<Car2>{
	
}

class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;
	public ChassisBuilder(CarQueue cq) {
		// TODO 自动生成的构造函数存根
		carQueue = cq;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				Car2 c = new Car2(counter++);
				print("ChassisBuilder created " + c);
				carQueue.put(c);
			}
		} catch (InterruptedException e) {
			print("Interrupted: ChassisBuilder");
		}
		
		print("ChassisBuilder off");
	}
}

class Assembler implements Runnable {

	private CarQueue chassisQueue, finishingQueue;
	private Car2 car;
	private CyclicBarrier barrier = new CyclicBarrier(4);
	private RobotPool robotPool;
	
	public Assembler(CarQueue cq, CarQueue fq, RobotPool rp) {
		// TODO 自动生成的构造函数存根
		chassisQueue = cq;
		finishingQueue = fq;
		robotPool = rp;
	}
	
	public Car2 car() {
		return car;
	}
	
	public CyclicBarrier barrier() {
		return barrier;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				car = chassisQueue.take();
				
				robotPool.hire(EngineRobot.class, this);
				robotPool.hire(DriveTrainRobot.class, this);
				robotPool.hire(WheelRobot.class, this);
				
				barrier.await();
				
				finishingQueue.put(car);
				
			}
		} catch (InterruptedException e) {
			print("Exiting Assembler via interrupt");
		} catch(BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		
		print("Assembler off");
	}
	
}

class Reporter implements Runnable {

	private CarQueue carQueue;
	
	public Reporter(CarQueue cq) {
		carQueue = cq;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				print(carQueue.take());
			}
		} catch(InterruptedException e) {
			print("Exiting Reporter via interrupt");
		}
		
		print("Reporter off");
	}
	
}

abstract class Robot implements Runnable {
	private RobotPool pool;
	public Robot(RobotPool p) {
		// TODO 自动生成的构造函数存根
		pool = p;
	}
	
	protected Assembler assembler;
	public Robot assignAssembler(Assembler assembler) {
		this.assembler = assembler;
		return this;
	}
	
	private boolean engage = false;
	public synchronized void engage() {
		engage = true;
		notifyAll();
	}
	
	
	abstract protected void performService();
	
	public void run() {
		try {
			powerDown();
			while(!Thread.interrupted()) {
				performService();
				assembler.barrier().await();
				powerDown();
			}
		} catch(InterruptedException e) {
			print("Exiting " + this + " via interrupt");
		} catch(BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		
		print(this + " off");
	}
	
	private synchronized void powerDown() throws InterruptedException {
		engage = false;
		assembler = null;
		pool.release(this);
		while(engage == false)
			wait();
	}
	
	public String toString() {
		return getClass().getName();
	}
}

class EngineRobot extends Robot {

	public EngineRobot(RobotPool p) {
		super(p);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected void performService() {
		print(this + " installing engine");
		assembler.car().addEngine();
	}
	
}

class DriveTrainRobot extends Robot {

	public DriveTrainRobot(RobotPool p) {
		super(p);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected void performService() {
		print(this + " installing DriveTrain");
		assembler.car().addDriveTrain();
	}
	
}

class WheelRobot extends Robot {

	public WheelRobot(RobotPool p) {
		super(p);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected void performService() {
		print(this + " installing Wheels");
		assembler.car().addWheels();
	}
	
}

class RobotPool {

	private Set<Robot> pool = new HashSet<Robot>();
	public synchronized void add(Robot r) {
		pool.add(r);
		notifyAll();
	}
	
	
	public void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
		for(Robot r : pool) {
			if(r.getClass().equals(robotType)) {
				pool.remove(r);
				r.assignAssembler(d);
				r.engage();
				return;
			}
		}
		wait();
		hire(robotType, d);
	}

	public synchronized void release(Robot robot) {
		add(robot);
	}
	
}


public class CarBuilder {

	public static void main(String[] args) throws InterruptedException {
		CarQueue chassisQueue = new CarQueue(),
				finishingQueue = new CarQueue();
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WheelRobot(robotPool));
		exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
		exec.execute(new Reporter(finishingQueue));
		
		exec.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
	}

}
