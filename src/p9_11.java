

interface Processor{
	String name();
	Object process(Object input);
}

class Apply{
	public static void process(Processor p, Object s){
		System.out.print("Using Processor " + p.name());
		System.out.print(p.process(s));
	}
}

class A{
	public String exchange(String str){
		return null;
	}
}

//��������
class AAdaptor implements Processor{
	A a = new A();
	@Override
	public String name() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Object process(Object input) {
		// TODO �Զ����ɵķ������
		return a.exchange((String)input);
	}
	
}
public class p9_11 {
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		B.test(new B());
	}
}
