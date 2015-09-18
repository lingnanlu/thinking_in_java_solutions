class A{
	class InnerA{
		class InnerInnerA{
			
		}
	}
}

class B{
	static class NestB{
		static class NestNestB{
			
		}
	}
}
public class p10_19 {
	B.NestB.NestNestB b = new B.NestB.NestNestB();
}
