import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



class DynamicProxyHandler implements InvocationHandler{

	private int count;
	private Object proxied;
	public DynamicProxyHandler(Object proxied) {
		// TODO 自动生成的构造函数存根
		this.proxied = proxied;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO 自动生成的方法存根
		//System.out.print(proxy);
		System.out.println("**** proxy: " + proxy.getClass() + ", method: "
				+ method + ", args: " + args);
		if(method.getName().equals("doSomething")){
			count++;
			System.out.println(count);
		}
		if(args != null){
			for(Object arg : args){
				System.out.println(" " + arg);
			}
		}
		return method.invoke(proxied, args);
	}
	
}
public class SimpleDynamicProxy {
	public static void consumer(Interface iface){
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	
	public static void main(String[] args){
		RealObject real = new RealObject();
		consumer(real);
		
		DynamicProxyHandler dyproxy = new DynamicProxyHandler(real);
		Interface proxy = (Interface)Proxy.newProxyInstance(
				Interface.class.getClassLoader(),
				new Class[]{Interface.class},
				dyproxy);
		
		Interface anotherproxy = 
				(Interface)Proxy.newProxyInstance(
						Interface.class.getClassLoader(),
						new Class[]{Interface.class},
						dyproxy);
		
		consumer(proxy);
		consumer(anotherproxy);
		
	}
}
