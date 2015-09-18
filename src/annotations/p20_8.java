package annotations;

import io.lingnanlu.github.OSExecute;
import net.mindview.atunit.Test;
import net.mindview.atunit.TestProperty;

class foobar{
	private String methodOne(){
		return "methodOne";
	}
	
	@TestProperty
	protected String methodOneCall(){
		return this.methodOne();
	}
}
public class p20_8 extends foobar{

	@Test
	boolean _methodOneCall(){
		return methodOneCall().equals("methodOne");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		OSExecute.command("java net.mindview.atunit.AtUnit annotations/p20_8");
	}

}
