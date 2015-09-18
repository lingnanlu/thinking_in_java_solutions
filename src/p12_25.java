class Base extends Exception {}

class Derived extends Base {}

class DerivedDerived extends Derived {}



public class p12_25 {
	class A{
		void foobar() throws Base{
			throw new Base();
		}
	}

	class B extends A{
		void foobar() throws Derived{
			throw new Derived();
		}
	}

	class C extends B{
		void foobar() throws DerivedDerived{
			throw new DerivedDerived();
		}
		
		void anotherfunc() throws DerivedDerived{
			foobar();
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		A a = new p12_25().new C();
		try{
			a.foobar();
		} catch(Base e){
			e.printStackTrace();
		}
		
	}

}
