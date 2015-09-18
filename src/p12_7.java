import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;


public class p12_7 {
	
	static Logger logger = Logger.getLogger("ArrayException");

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] a = {0, 1, 2};
		try{
			int i = a[5];
		} catch(ArrayIndexOutOfBoundsException e){
			StringWriter trace = new StringWriter();
			e.printStackTrace(new PrintWriter(trace));
			logger.severe(trace.toString());
			
		}
	}

}
