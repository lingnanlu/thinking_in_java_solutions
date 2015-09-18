package concurrency;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.lingnanlu.github.Print.*;


class WebClient {
	private final int serviceTime;
	public WebClient(int tm) {
		// TODO 自动生成的构造函数存根
		serviceTime = tm;
	}
	
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public String toString() {
		return "[" + serviceTime + "]";
	}
	
	
	
}


class WebClientLine extends ArrayBlockingQueue<WebClient> {
	public WebClientLine(int maxLineSize) {
		// TODO 自动生成的构造函数存根
		super(maxLineSize);
	}
	
	
	public String toString() {
		if(this.size() == 0)
			return "[Empty]";
		
		StringBuilder result = new StringBuilder();
		
		for(WebClient client : this)
			result.append(client);
		
		return result.toString();
	}
}

class WebClientGenerator implements Runnable {

	
	private WebClientLine clients;
	volatile int loadFactor = 1;
	private static Random rand = new Random(47);
	
	public WebClientGenerator(WebClientLine cq) {
		// TODO 自动生成的构造函数存根
		clients = cq;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				clients.put(new WebClient(rand.nextInt(1000)));
				TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
			}
		} catch (InterruptedException e){
			print("WebClientGenerator interrupted");
		}
		
		print("WebClientGenerator terminating");
	}
	
}

class Server implements Runnable {

	private static int counter;
	private final int id = counter++;
	private WebClientLine clients;
	
	public Server(WebClientLine cq) {
		// TODO 自动生成的构造函数存根
		clients = cq;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				WebClient client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
			}
		} catch (InterruptedException e) {
			print(this + "interrupted");
		}
		
		print(this + "terminating");
	}
	
	
	public String toString() {
		return "Server " + id + " ";
	}
	
	public String shortString(){
		return "T" + id;
	}
	
}


class SimulationManager implements Runnable {

	private ExecutorService exec;
	private WebClientGenerator gen;
	private WebClientLine clients;
	private Queue<Server> servers = new LinkedList<Server>();
	
	private int adjustmentPeriod;
	
	private boolean stable = true;
	private int prevSize;
	
	public SimulationManager(ExecutorService e, WebClientGenerator gen, WebClientLine clients,
			int adjustmentPerios, int n) {
		// TODO 自动生成的构造函数存根
		this.gen = gen;
		this.clients = clients;
		this.adjustmentPeriod = adjustmentPeriod;
		exec = e;
		
		for(int i = 0; i < n; i++) {
			Server server = new Server(clients);
			exec.execute(server);
			servers.add(server);
		}
	}
	
	public void adjustLoadFactor() {
		if(clients.size() > prevSize) {
			if(stable)
				stable = false;
			else if(!stable) {
				print("Peak load factor: ~" + gen.loadFactor);
				exec.shutdownNow();
			}
		} else {
			print("New load factor: " + ++gen.loadFactor);
			stable = true;
		}
		
		prevSize = clients.size();
	}
	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				System.out.print(clients + "{ ");
				for(Server server : servers){
					printnb(server.shortString() + " ");
				}
				print("}");
				adjustLoadFactor();
			}
		} catch(InterruptedException e) {
			print(this + "interrupted");
		}
		
		System.out.println(this + "terminating");
	}
	
	public String toString() {
		return "SimulationManager ";
	}
	
}
public class p21_35 {
	
	static final int MAX_LINE_SIZE = 50;
	static final int NUM_OF_SERVERS = 3;
	static final int ADJUSTMENT_PERIOD = 1000;
	
	public static void main(String[] args) throws NumberFormatException, InterruptedException, IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		WebClientLine clients = new WebClientLine(MAX_LINE_SIZE);
		
		WebClientGenerator g = new WebClientGenerator(clients);
		
		exec.execute(g);
		
		exec.execute(new SimulationManager(exec, g, clients, ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
		
		if(args.length > 0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
		} else {
			System.out.println("Press 'ENTER' to quit");
			System.in.read();
		}
		
		exec.shutdownNow();
	}

}
