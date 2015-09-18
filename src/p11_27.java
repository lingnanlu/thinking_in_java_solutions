import java.util.*;

class Command{
	private String command;
	Command(String command){
		this.command = command;
	}
	
	public void operation(){
		System.out.println(command);
	}
}

class A{
	public static Queue<Command> foobar(Command[] commands){
		Queue<Command> commandQueue = new LinkedList<Command>();
		for (Command command : commands){
			commandQueue.offer(command);
		}
		
		return commandQueue;
	}
}
public class p11_27 {
	
	public static void consume(Queue<Command> commandQueue){
		while(commandQueue.peek() != null){
			commandQueue.remove().operation();
		}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Command[] commands = {new Command("consue"), new Command("peek"),
				new Command("hehe")};
		Queue<Command> queue = A.foobar(commands);
		
		consume(queue);
		
	}

}
