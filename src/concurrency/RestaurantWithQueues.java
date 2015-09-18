package concurrency;

import enumerated.menu.*;

import java.util.concurrent.*;
import java.util.*;

import static io.lingnanlu.github.Print.*;

class Order {
	private static int counter = 0;
	private final int id = counter++;
	private final Customer customer;
	private final WaitPerson waitPerson;
	private final Food food;
	
	public Order(Customer cust, WaitPerson wp , Food f) {
		// TODO 自动生成的构造函数存根
		customer = cust;
		waitPerson = wp;
		food = f;
	}
	
	public Food item() {
		return food;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public WaitPerson getWaitPerson() {
		return waitPerson;
	}
	
	public String toString() {
		return "Order: " + id + " item: " + food + " for: " + customer + " served by: " + waitPerson;
	}
}


class Plate {
	private final Order order;
	private final Food food;
	public Plate(Order ord, Food f) {
		// TODO 自动生成的构造函数存根
		order = ord;
		food = f;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public Food getFood() {
		return food;
	}
	
	public String toString() {
		return food.toString();
	}
}

class WaitPerson implements Runnable{
	
	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	BlockingQueue<Plate> filledOrders = 
			new LinkedBlockingDeque<Plate>();
	public WaitPerson(Restaurant rest){
		restaurant = rest;
	}
	
	public void placeOrder(Customer cust, Food food) {
		try{
			restaurant.orders.put(new Order(cust, this, food));
		} catch(InterruptedException e) {
			print(this + " placeOrder interrupted");
		}

	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Plate plate = filledOrders.take();
				print(this + "received " + plate + " delivering to " + 
						plate.getOrder().getCustomer());
				plate.getOrder().getCustomer().deliver(plate);
			}
		} catch(InterruptedException e) {
			print(this + " interrupted");
		}
		
		print(this + "off duty");
	}
	
	public String toString() {
		return "WaitPerson " + id + " ";
	}
	
	
}

class Chef implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private final Restaurant restaurant;
	private static Random rand = new Random(47);
	public Chef(Restaurant rest) {
		restaurant = rest;
	}
	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted()) {
				Order order = restaurant.orders.take();
				Food requestedItem = order.item();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				Plate plate = new Plate(order, requestedItem);
				order.getWaitPerson().filledOrders.put(plate);
			}
		} catch(InterruptedException e) {
			print(this + " interrupted");
		}
		
		print(this + " off duty");
	}
	
	public String toString() {
		return "Chef " + id + " ";
	}
	
}

class Restaurant implements Runnable {

	private List<WaitPerson> waitPersons = new ArrayList<WaitPerson>();
	private List<Chef> chefs = new ArrayList<Chef>();
	private ExecutorService exec;
	private static Random rand = new Random(47);
	BlockingQueue<Order> orders = 
			new LinkedBlockingDeque<Order>();
	
	public Restaurant(ExecutorService e, int nWaitPersons, int nChefs) {
		// TODO 自动生成的构造函数存根
		
		exec = e;
		for(int i = 0; i < nWaitPersons; i++) {
			WaitPerson waitPerson = new WaitPerson(this);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
		}
		
		for(int i = 0; i < nChefs; i++) {
			Chef chef = new Chef(this);
			chefs.add(chef);
			exec.execute(chef);
		}
	}
	@Override
	public void run() {
		
		try{
			while(!Thread.interrupted()) {
				WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
				Customer c = new Customer(wp);
				exec.execute(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			print("Restaurant interrupted");
		}
		
		print("Restaurant closing");
	}
	
}

class Customer implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private final WaitPerson waitPerson;
	
	private SynchronousQueue<Plate> placeSetting = 
			new SynchronousQueue<Plate>();
	public Customer(WaitPerson w) {
		// TODO 自动生成的构造函数存根
		waitPerson = w;
	}
	
	public void deliver(Plate p) throws InterruptedException{
		placeSetting.put(p);
	}
	@Override
	public void run() {
		for(Course course : Course.values()) {
			Food food = course.randomSelection();
			
			try {
				waitPerson.placeOrder(this, food);
				print(this + "eating " + placeSetting.take());
			} catch(InterruptedException e) {
				print(this + "waiting for " + course + " interrupted");
				break;
			}
		}
		
		print(this + "finished meal, leaving");
	}
	
	public String toString() {
		return "Customer " + id + " ";
	}
	
}
public class RestaurantWithQueues {

	public static void main(String[] args) throws Exception, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Restaurant restaurant = new Restaurant(exec, 5, 2);
		exec.execute(restaurant);
		if(args.length > 0)
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
		else {
			print("Press 'Enter' to quit");
			System.in.read();
		}
		
		exec.shutdownNow();
	}

}
