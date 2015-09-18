package concurrency;

import enumerated.menu.*;

import java.util.concurrent.*;
import java.util.*;

import static io.lingnanlu.github.Print.*;

class Order2 {
	
}

class Plate2 {
	
}


class TableManager extends ArrayBlockingQueue<Table>{
	
	public TableManager(int n) {
		super(n);
		// TODO 自动生成的构造函数存根
		for(int i = 0; i < n; i++){
			this.add(new Table(this));
		}
	}

}

class Table {

	private static int counter = 0;
	private final int id = counter++;
	TableManager tm;
	WaitPerson2 waitPerson;
	List<Customer2> customers;
	ExecutorService exec;
	BlockingQueue<Plate2> plates = new LinkedBlockingQueue<Plate2>();
	public Table(TableManager tm) {
		// TODO 自动生成的构造函数存根
		this.tm = tm;
	}
	public void init(WaitPerson2 waitPerson, List<Customer2> customers){
		this.waitPerson = waitPerson;
		this.customers = customers;
		
		for(Customer2 customer : this.customers) {
			customer.setTable(this);
		}
	}
	
	public void start() {
		CyclicBarrier barrier = new CyclicBarrier(customers.size(), new Runnable() {
			
			//false表示点完菜了，true表示吃完了
			boolean flag = false;
			@Override
			public void run() {
				if(flag == false){
					
					//点完菜后，服务员提交订单
					waitPerson.placeOrder();
				} else {
					
					//吃完后回收桌子

					try {
						tm.put(Table.this);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		
				}
				
			}
			
		});
		
		for(int i = 0; i < customers.size(); i++) {
			Customer2 customer = customers.get(i);
			customer.setBarrier(barrier);
			exec.execute(customer);
		}
		
	}

}



class WaitPerson2 implements Runnable{
	
	private static int counter = 0;
	private final int id = counter++;
	BlockingQueue<Order2> orders;
	
	public WaitPerson2(BlockingQueue<Order2> orders) {
		// TODO 自动生成的构造函数存根
		this.orders = orders;
	}
	
	public void placeOrder() {
		try {
			orders.put(new Order2());
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	}
	
}
class Chef2 implements Runnable{
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	BlockingQueue<Order2> orders;
	
	public Chef2(BlockingQueue<Order2> orders) {
		// TODO 自动生成的构造函数存根
		this.orders = orders;
	}
	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted()) {
				Order2 order = orders.take();
				
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				
				
			}
		} catch(InterruptedException e){
			
		}
	}
	
}





class Customer2 implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	
	Random rand = new Random(47);
	CyclicBarrier barrier;
	Table tb;
	public void setBarrier(CyclicBarrier barrier) {
		// TODO 自动生成的构造函数存根
		this.barrier = barrier;
	}

	
	public void setTable(Table tb) {
		this.tb = tb;
	}
	
	
	@Override
	public void run() {
	
		
		//等待同一桌的其它人点菜
		try {
			Course[] courses = Course.values();
			Food food = courses[rand.nextInt(courses.length)].randomSelection();
			
			//第一个栅栏处，等待别人点菜，大家都点完后就上菜
			barrier.await();
			
			//如果栅栏处的动作没执行完，其它线程依然阻塞
			//栅栏动作执行完成，就等待拿菜
			print(this + " 正在吃 " + tb.plates.take());
			
			//模拟吃饭的时间
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
			
			
			//第二个栅栏处，等待别人吃完
			barrier.await();
			
			print(this + " 吃完了 ");
			
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
}



//每次生产1-4人不同的Customer
class CustomerFactory implements Runnable{

	Random rand = new Random(47);
	BlockingQueue<List<Customer2>> customersLine;
	
	public CustomerFactory(BlockingQueue<List<Customer2>> customersLine) {
		// TODO 自动生成的构造函数存根
		this.customersLine = customersLine;
	}
	@Override
	public void run() {
		int n;
		while(!Thread.interrupted()){
			n = rand.nextInt(4) + 1;
			List<Customer2> customers = new LinkedList<Customer2>();
			for(int i = 0; i < n; i++) {
				customers.add(new Customer2());
			}
			
			customersLine.add(customers);
		}
	}
	
}



class Restaurant2 implements Runnable {
	private static Random rand = new Random(47);
	private static final int NUM_OF_TABLES = 20;
	List<Chef2> chefs = new ArrayList<Chef2>();
	List<WaitPerson2> waitPersons = new ArrayList<WaitPerson2>();
	TableManager tm = new TableManager(NUM_OF_TABLES);
	ExecutorService exec;
	BlockingQueue<Order2> orders = new LinkedBlockingDeque<Order2>();
	BlockingQueue<List<Customer2>> customersLine;
	public Restaurant2(int nChef, int nWaitPerson, BlockingQueue<List<Customer2>> customersLine, ExecutorService e) {
		
		// TODO 自动生成的构造函数存根
		this.exec = e;
		this.customersLine = customersLine;
		
		
		//生成厨师，并让他们忙起来，当然，一开始没有Order所以都在等待。
		for(int i = 0; i < nChef; i++) {
			Chef2 chef = new Chef2(orders);
			chefs.add(chef);
			exec.execute(chef);
		}
		
		for(int i = 0; i < nWaitPerson; i++) {
			WaitPerson2 waitPerson = new WaitPerson2(orders);
			waitPersons.add(waitPerson);
			exec.execute(waitPerson);
			
		}
		

	}
	
	
	@Override
	public void run() {
		try {
			
			//从排队的顾客中取出下一队。
			List<Customer2> customers = customersLine.take();
			
			
			//取出一张空桌
			Table table = tm.take();
			
			
			//为该桌分派服务员和顾客，可见一个服务员可服务于多张桌子
			table.init(waitPersons.get(rand.nextInt(waitPersons.size())), customers);
			
			table.start();
		} catch (InterruptedException e) {
			
		}
	}
	
}

public class p21_36 {

	public static void main(String[] args) throws Exception, InterruptedException {
		
	}

}
