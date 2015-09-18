import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class MyExceptionOne extends Exception{
	private static Logger logger = Logger.getLogger("MyExceptionOne");
	
	MyExceptionOne(){
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

class MyExceptionTwo extends Exception{
	private static Logger logger = Logger.getLogger("MyExceptionTwo");
	
	
	
	MyExceptionTwo(){
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
	

}
public class p12_6 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try{
			throw new MyExceptionOne();
		} catch(MyExceptionOne e){
			
		}
		
		try{
			throw new MyExceptionTwo();
		} catch(MyExceptionTwo e){
			
		}
	}

}
