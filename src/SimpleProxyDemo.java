import static net.mindview.util.Print.*;

interface Interface{
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface{

	@Override
	public void doSomething() {
		// TODO �Զ����ɵķ������
		print("dosomething");
	}

	@Override
	public void somethingElse(String arg) {
		// TODO �Զ����ɵķ������
		print("somethingelse " + arg);
	}
	
}

class SimpleProxy implements Interface{
	private int count = 0;
	private Interface proxied;
	public SimpleProxy(Interface proxied){
		this.proxied = proxied;
	}
	
	@Override
	public void doSomething() {
		// TODO �Զ����ɵķ������
		print("SimpleProxy doSomething");
		count++;
		print(count);
		proxied.doSomething();
	}

	@Override
	public void somethingElse(String arg) {
		// TODO �Զ����ɵķ������
		print("SimpleProxy somethingElse ");
		proxied.somethingElse(arg);
	}
	
}
public class SimpleProxyDemo {
	public static void consumer(Interface iface){
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	
	public static void main(String[] args){
		RealObject real = new RealObject();
		//consumer(new RealObject());
		Interface proxy = new SimpleProxy(real);
		consumer(proxy);
		consumer(proxy);

		consumer(proxy);

		consumer(proxy);

		consumer(proxy);

		consumer(proxy);

		
	}
}
