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
		// TODO �Զ����ɵĹ��캯�����
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
		// TODO �Զ����ɵĹ��캯�����
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
			
			//false��ʾ������ˣ�true��ʾ������
			boolean flag = false;
			@Override
			public void run() {
				if(flag == false){
					
					//����˺󣬷���Ա�ύ����
					waitPerson.placeOrder();
				} else {
					
					//������������

					try {
						tm.put(Table.this);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
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
		// TODO �Զ����ɵĹ��캯�����
		this.orders = orders;
	}
	
	public void placeOrder() {
		try {
			orders.put(new Order2());
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
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
		// TODO �Զ����ɵĹ��캯�����
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
		// TODO �Զ����ɵĹ��캯�����
		this.barrier = barrier;
	}

	
	public void setTable(Table tb) {
		this.tb = tb;
	}
	
	
	@Override
	public void run() {
	
		
		//�ȴ�ͬһ���������˵��
		try {
			Course[] courses = Course.values();
			Food food = courses[rand.nextInt(courses.length)].randomSelection();
			
			//��һ��դ�������ȴ����˵�ˣ���Ҷ��������ϲ�
			barrier.await();
			
			//���դ�����Ķ���ûִ���꣬�����߳���Ȼ����
			//դ������ִ����ɣ��͵ȴ��ò�
			print(this + " ���ڳ� " + tb.plates.take());
			
			//ģ��Է���ʱ��
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
			
			
			//�ڶ���դ�������ȴ����˳���
			barrier.await();
			
			print(this + " ������ ");
			
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
}



//ÿ������1-4�˲�ͬ��Customer
class CustomerFactory implements Runnable{

	Random rand = new Random(47);
	BlockingQueue<List<Customer2>> customersLine;
	
	public CustomerFactory(BlockingQueue<List<Customer2>> customersLine) {
		// TODO �Զ����ɵĹ��캯�����
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
		
		// TODO �Զ����ɵĹ��캯�����
		this.exec = e;
		this.customersLine = customersLine;
		
		
		//���ɳ�ʦ����������æ��������Ȼ��һ��ʼû��Order���Զ��ڵȴ���
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
			
			//���ŶӵĹ˿���ȡ����һ�ӡ�
			List<Customer2> customers = customersLine.take();
			
			
			//ȡ��һ�ſ���
			Table table = tm.take();
			
			
			//Ϊ�������ɷ���Ա�͹˿ͣ��ɼ�һ������Ա�ɷ����ڶ�������
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
